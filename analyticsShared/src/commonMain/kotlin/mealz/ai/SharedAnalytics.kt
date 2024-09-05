package mealz.ai

import ai.mealz.analytics.getPlatform
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
import kotlinx.serialization.json.Json
import kotlin.js.JsExport
import kotlin.js.JsName

// TODO
// test with ios
// test with android
// test with npm
// build with wasm?
// add emitters
// add log levels
// get device with expect/actual
// add all functions
// update web
// update android
// update ios

typealias onEmitFunction = (PlausibleEvent) -> Unit

@JsExport
public object SharedAnalytics {
    private const val PLAUSIBLE_URL: String = "https://plausible.io/api/event"
    private val coroutineHandler = CoroutineExceptionHandler { _, exception ->
        println("Mealz error in Analytics $exception ${exception.stackTraceToString()}")
    }
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main)

    private val domain: MutableStateFlow<String?> = MutableStateFlow(null)
    private val httpOrigin: MutableStateFlow<String?> = MutableStateFlow(null)
    private val version: MutableStateFlow<String> = MutableStateFlow("")

    private val alreadyInitialized: Boolean
        get() = !domain.value.isNullOrBlank() && !httpOrigin.value.isNullOrBlank() && version.value.isNotBlank()

    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json)
        }
        BrowserUserAgent()
        install(DefaultRequest)
    }

    private lateinit var onEmit: onEmitFunction

    fun init(supplierOrigin: String, version: String, onEmit: onEmitFunction) {

        if (alreadyInitialized) return
        domain.value = supplierOrigin
        val isHttp = supplierOrigin.startsWith("https://")
        httpOrigin.apply { value = if (isHttp) supplierOrigin else "https://$supplierOrigin" }
        this.version.value = version
        println("Analytics init for ${domain.value}")
        this.onEmit = onEmit;
    }

    private suspend fun HttpClient.postEvent(event: PlausibleEvent) {
        println("will send event $event to $PLAUSIBLE_URL")
        post(PLAUSIBLE_URL) {
            contentType(ContentType.Application.Json)
            setBody(event)
        }
    }

    internal fun buildAndSendPlausibleRequest(eventType: String, path: String, props: PlausibleProps) {
        domain.value?.let { domain ->
            httpOrigin.value?.let { httpOrigin ->
                val propsWithVersionAndDevice = props.copy(version = version.value, device = getPlatform().name)
                val url = httpOrigin + path
                val event = PlausibleEvent(
                    eventType,
                    url,
                    domain,
                    propsWithVersionAndDevice
                )
                onEmit(event)
                coroutineScope.launch(coroutineHandler) {
                    httpClient.postEvent(event)
                }
            } ?: println("Cannot send event without httpOrigin initialisation ${httpOrigin.value}")
        } ?: println("Cannot send event without domain initialisation ${domain.value}")
    }
}