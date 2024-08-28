package mealz.ai

import io.ktor.client.HttpClient
import io.ktor.client.plugins.BrowserUserAgent
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
public data class AnalyticEvent(val eventType: String, val path: String, val props: PlausibleProps)


@Suppress("UserPreferencesInstance used by ios and component")
@JsExport
public object AnalyticsInstance {
    @JsName("instance")
    public val instance: Analytics = AnalyticsImp()
}

@JsExport
public enum class PlausiblePaths(public val path: String) {
    RECIPE_MEAL_PLANNER("miam/recipes/meals-planner"),
    RECIPE_MEAL_PLANNER_RESULT("miam/recipes/meals-planner/results"),
    RECIPE_MEAL_PLANNER_CATALOG("miam/recipes/meals-planner/catalog"),
    RECIPE_MEAL_PLANNER_BASKET_PREVIEW("miam/recipes/meals-planner/basket-preview"),
    RECIPE_MEAL_PLANNER_CONFIRMATION("miam/recipes/meals-planner/confirmation")
}

@JsExport
@Serializable
public data class PlausibleProps(
    val recipe_id: String? = null,
    val category_id: String? = null,
    val entry_name: String? = null,
    val basket_id: String? = null,
    val miam_amount: Float? = null,
    val total_amount: String? = null,
    val pos_id: String? = null,
    val pos_total_amount: String? = null,
    val pos_name: String? = null,
    val search_term: String? = null,
    val uses_count: String? = null,
    val time_passed: String? = null,
    val budget_user: String? = null,
    val budget_planner: String? = null,
    val recipe_count: String? = null,
    val query: String? = null,
    val guests: String? = null,
    val item_id: String? = null,
    val item_ean: String? = null,
    val user_preference: String? = null,
    val old_item_id: String? = null,
    val old_item_ean: String? = null,
    val new_item_id: String? = null,
    val new_item_ean: String? = null,
    val recipe_item_id: String? = null,
    val diff: String? = null,
    val from_miam: Boolean? = null,
    val miam_products: Int? = null,
    val total_products: Int? = null,
    val product_quantity: Int? = null,
    val client_order_id: String? = null,
    val version: String? = null,
    val device: String? = null
)

@JsExport
@Serializable
public data class PlausibleEvent(
    val name: String,
    val url: String,
    val domain: String,
    val props: PlausibleProps
)

@JsExport
public interface Analytics {
    public fun emitEvent(event: AnalyticEvent)
    @JsName("sendAnalyticEvent")
    public fun sendEvent(eventType: String, path: String)
    @JsName("initialize")
    public fun init(supplierOrigin: String)
    @JsName("sendRecipeDisplay")
    public fun sendRecipeDisplay(recipeId: String, path: String)
}

public object PlausibleDestinations {
    public const val PLAUSIBLE_URL: String = "https://plausible.io/api/event"

    public const val EVENT_PAGEVIEW: String =
        "pageview" // GA4 is page_view -> use this one on ga to autotrack some fields
    // ---------------------------------- RECIPE ----------------------------------------------
    public const val EVENT_RECIPE_SHOW: String = "recipe.show"
    public const val EVENT_RECIPE_DISPLAY: String = "recipe.display"
    public const val EVENT_RECIPE_ADD: String = "recipe.add"
    public const val EVENT_RECIPE_REMOVE: String = "recipe.remove"
    public const val EVENT_RECIPE_RESET: String = "recipe.reset"
    public const val EVENT_RECIPE_CHANGEGUESTS: String = "recipe.change-guests"
    public const val EVENT_RECIPE_LIKE: String = "recipe.like"
    public const val EVENT_RECIPE_UNLIKE: String = "recipe.unlike"
    public const val EVENT_RECIPE_PRINT: String = "recipe.print"
    public const val EVENT_PERSONAL_RECIPE_CREATE: String = "recipe.personal.create"
    public const val EVENT_PERSONAL_RECIPE_DELETE: String = "recipe.personal.delete"
    // ---------------------------------- CATALOG ----------------------------------------------
    public const val EVENT_SEARCH: String = "search"
    public const val EVENT_CATEGORY_SHOW: String = "category.show"
    public const val EVENT_PREFERENCE_SELECTED: String = "preference.selected"
    // ---------------------------------- PRODUCT ----------------------------------------------
    public const val EVENT_ENTRY_ADD: String = "entry.add"
    public const val EVENT_ENTRY_DELETE: String = "entry.delete"
    public const val EVENT_ENTRY_REPLACE: String = "entry.replace"
    public const val EVENT_ENTRY_CHANGE_QUANTITY: String = "entry.change-quantity"
    // ---------------------------------- PAYMENT ----------------------------------------------
    public const val EVENT_PAYMENT_STARTED: String = "payment.started"
    public const val EVENT_PAYMENT_CONFIRMED: String = "payment.confirmed"
    // ---------------------------------- BASKET ----------------------------------------------
    public const val EVENT_BASKET_CONFIRMED: String = "basket.confirmed"
    public const val EVENT_BASKET_PREVIEW: String = "basket.preview"
    // ------------------------------- MEAL PLANNER -------------------------------------------
    public const val EVENT_PLANNER_CONFIRM: String = "planner.confirm"
    public const val EVENT_PLANNER_CLOSE: String = "planner.close"
    public const val EVENT_PLANNER_SEARCH: String = "search"
    // ------------------------------- STORE LOCATOR -------------------------------------------
    public const val EVENT_LOCATOR_NAVIGATE: String = "locator.navigate"
    public const val EVENT_POS_SELECTED: String = "pos.selected"
}

@JsExport
public class AnalyticsImp : Analytics {
    private val coroutineHandler = CoroutineExceptionHandler { _, exception ->
        println("Miam error in Analytics $exception ${exception.stackTraceToString()}")
    }
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main)
    @JsName("originToDomain")
    private val originToDomain: Map<String, String> = mapOf(
        "app.coursesu.com" to "miam.coursesu.app",
        "app.coursesu" to "miam.coursesu.app",
        "app.qualif.coursesu" to "miam.test"
    )
    private val domain: MutableStateFlow<String?> = MutableStateFlow(null)
    private val httpOrigin: MutableStateFlow<String?> = MutableStateFlow(null)


    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json)
        }
        BrowserUserAgent()
        install(DefaultRequest)
    }

    private suspend fun HttpClient.postEvent(event: PlausibleEvent) {
        println("will send event $event to ${PlausibleDestinations.PLAUSIBLE_URL}")
        post(PlausibleDestinations.PLAUSIBLE_URL) {
            contentType(ContentType.Application.Json)
            setBody(event)
        }
    }


    override fun init(supplierOrigin: String) {
        domain.apply { value = originToDomain[supplierOrigin] ?: supplierOrigin }
        val isHttp = supplierOrigin.startsWith("https://")
        httpOrigin.apply { value = if (isHttp) supplierOrigin else "https://$supplierOrigin" }
        println("Analytics init for $domain")
    }

    override fun emitEvent(event: AnalyticEvent) {
//        MealzDI.analyticsNotifier.push(event)
    }

    override fun sendEvent(eventType: String, path: String) {
        val props = PlausibleProps()
        if (this.domain.value == null || this.httpOrigin.value == null) {
            println("Sending event without initialisation ${domain.value}")
            return
        }
//        val propsWithVersionAndDevice = props.copy(version = SdkVersion.VERSION, device = getOperatingSystem().name)
        val propsWithVersionAndDevice = props
        emitEvent(AnalyticEvent(eventType, path, propsWithVersionAndDevice))
        val domain = this.domain.value!!
        val url = this.httpOrigin.value!! + path
        val event = PlausibleEvent(
            eventType,
            url,
            domain,
            propsWithVersionAndDevice
        )
        coroutineScope.launch(coroutineHandler) {
            httpClient.postEvent(event)
        }
    }

    private fun buildRequest(eventType: String, path: String, props: PlausibleProps) {
        if (this.domain.value == null || this.httpOrigin.value == null) {
            println("Sending event without initialisation ${domain.value}")
            return
        }
        emitEvent(AnalyticEvent(eventType, path, props))
        val domain = this.domain.value!!
        val url = this.httpOrigin.value!! + path
        val event = PlausibleEvent(
            eventType,
            url,
            domain,
            props
        )
        coroutineScope.launch(coroutineHandler) {
            httpClient.postEvent(event)
        }
    }

    override fun sendRecipeDisplay(recipeId: String, path: String) {
        val device = "" // this will come from expect/actual
        val version = "" // we need to work out a way to set this on each platform
        val propsWithVersionAndDevice = PlausibleProps(
            recipe_id = recipeId,
            device = device,
            version = version
        )
        this.buildRequest(PlausibleDestinations.EVENT_RECIPE_DISPLAY, path, propsWithVersionAndDevice)
    }
}