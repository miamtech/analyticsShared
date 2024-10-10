package ai.mealz.analytics

import ai.mealz.analytics.handler.LogHandler
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

actual object SharedAnalytics : AbstractSharedAnalytics() {
    private val gson = Gson()

    private val coroutineHandler = CoroutineExceptionHandler { _, exception ->
        LogHandler.error("Mealz error in Analytics $exception ${exception.stackTraceToString()}")
    }
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO + coroutineHandler)

    private val httpClient = HttpClient {
        install(DefaultRequest)
    }

    override fun sendRequest(event: PlausibleEvent) {
        LogHandler.info("Will send event $event to $PLAUSIBLE_URL")
        coroutineScope.launch {
            httpClient.post(PLAUSIBLE_URL) {
                contentType(ContentType.Application.Json)
                setBody(gson.toJson(event))
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
