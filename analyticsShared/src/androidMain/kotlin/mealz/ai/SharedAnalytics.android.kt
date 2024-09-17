package ai.mealz.analytics

import com.google.gson.Gson
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

actual object SharedAnalytics: AbstractSharedAnalytics() {
    private val gson = Gson()

    private val coroutineHandler = CoroutineExceptionHandler { _, exception ->
        println("Mealz error in Analytics $exception ${exception.stackTraceToString()}")
    }
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO + coroutineHandler)

    private val httpClient = HttpClient {
        install(DefaultRequest)
    }

    override fun sendRequest(event: PlausibleEvent) {
        println("will send event $event to $PLAUSIBLE_URL") // TODO: log and log levels task
        coroutineScope.launch {
            httpClient.post(PLAUSIBLE_URL) {
                contentType(ContentType.Application.Json)
                setBody(gson.toJson(event))
            }
        }
    }

    actual fun sendPlausibleRequest(plausiblePath: String, path: String, plausibleProps: PlausibleProps) {
        super.buildAndSendPlausibleRequest(plausiblePath, path, plausibleProps)
    }

    actual fun initSharedAnalytics(domain: String, version: String, abTestKey: String, affiliate: String, onEmit: onEmitFunction) {
        super.init(domain, version, abTestKey, affiliate, onEmit)
    }
}
