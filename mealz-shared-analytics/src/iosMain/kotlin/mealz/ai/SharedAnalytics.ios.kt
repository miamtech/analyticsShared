package ai.mealz.analytics

import ai.mealz.analytics.handler.LogHandler
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import platform.Foundation.NSData
import platform.Foundation.NSDictionary
import platform.Foundation.NSJSONSerialization
import platform.Foundation.NSString
import platform.Foundation.NSUTF8StringEncoding
import platform.Foundation.create

actual object SharedAnalytics : AbstractSharedAnalytics() {
    private val coroutineHandler = CoroutineExceptionHandler { _, exception ->
        LogHandler.error("Mealz error in Analytics $exception ${exception.stackTraceToString()}")
    }
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO + coroutineHandler)

    private val httpClient = HttpClient {
        install(DefaultRequest)
    }

    private fun plausiblePropsToDict(props: PlausibleProps): NSDictionary {
        return mapOf(
            "recipe_id" to props.recipe_id,
            "category_id" to props.category_id,
            "entry_name" to props.entry_name,
            "item_id" to props.item_id,
            "ext_item_id" to props.ext_item_id,
            "item_ean" to props.item_ean,
            "old_item_id" to props.old_item_id,
            "old_item_ext_id" to props.old_item_ext_id,
            "old_item_ean" to props.old_item_ean,
            "new_item_id" to props.new_item_id,
            "new_item_ext_id" to props.new_item_ext_id,
            "new_item_ean" to props.new_item_ean,
            "product_quantity" to props.product_quantity,
            "basket_id" to props.basket_id,
            "miam_amount" to props.miam_amount,
            "total_amount" to props.total_amount,
            "miam_products" to props.miam_products,
            "total_products" to props.total_products,
            "client_order_id" to props.client_order_id,
            "supplier_id" to props.supplier_id,
            "pos_id" to props.pos_id,
            "pos_name" to props.pos_name,
            "search_term" to props.search_term,
            "stores_found_count" to props.stores_found_count,
            "budget" to props.budget,
            "budget_user" to props.budget_user,
            "budget_planner" to props.budget_planner,
            "recipe_count" to props.recipe_count,
            "guests" to props.guests,
            "uses_count" to props.uses_count,
            "time_passed" to props.time_passed,
            "client_sdk_version" to props.client_sdk_version,
            "analytics_sdk_version" to props.analytics_sdk_version,
            "platform" to props.platform,
            "affiliate" to props.affiliate,
            "abTestKey" to props.abTestKey
        ) as NSDictionary
    }

    @OptIn(ExperimentalForeignApi::class, BetaInteropApi::class)
    private fun plausibleEventToJson(event: PlausibleEvent): NSString? {
        val propsDict = plausiblePropsToDict(event.props)

        val eventDict = mapOf(
            "name" to event.name,
            "url" to event.url,
            "domain" to event.domain,
            "props" to propsDict
        ) as NSDictionary

        val jsonData: NSData = NSJSONSerialization.dataWithJSONObject(eventDict, 0u, null)!!

        return NSString.create(data = jsonData, encoding = NSUTF8StringEncoding)
    }

    override fun sendRequest(event: PlausibleEvent) {
        LogHandler.info("will send event $event to $PLAUSIBLE_URL")
        coroutineScope.launch {
            httpClient.post(PLAUSIBLE_URL) {
                contentType(ContentType.Application.Json)
                setBody(plausibleEventToJson(event))
            }
        }
    }

    actual fun sendPlausibleRequest(plausiblePath: String, path: String, plausibleProps: PlausibleProps) {
        this.buildAndSendPlausibleRequest(plausiblePath, path, plausibleProps)
    }

    actual fun initSharedAnalytics(domain: String, version: String, onEmit: onEmitFunction) {
        this.init(domain, version, onEmit)
        LogHandler.info("Analytics init for $domain")
    }
}
