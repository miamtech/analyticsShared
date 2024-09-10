package ai.mealz.analytics

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

typealias onEmitFunction = (PlausibleEvent) -> Unit

@JsExport
object SharedAnalytics {
    private const val PLAUSIBLE_URL: String = "https://plausible.io/api/event"
    private val coroutineHandler = CoroutineExceptionHandler { _, exception ->
        println("Mealz error in Analytics $exception ${exception.stackTraceToString()}")
    }
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main)

    private val domain: MutableStateFlow<String?> = MutableStateFlow(null)
    private val version: MutableStateFlow<String> = MutableStateFlow("")
    private val affiliate: MutableStateFlow<String?> = MutableStateFlow(null)
    private val abTestKey: MutableStateFlow<String?> = MutableStateFlow(null)

    private val alreadyInitialized: Boolean
        get() = !domain.value.isNullOrBlank() && version.value.isNotBlank()

    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json)
        }
        BrowserUserAgent()
        install(DefaultRequest)
    }

    private lateinit var onEmit: onEmitFunction

    fun init(domain: String, version: String, onEmit: onEmitFunction) {
        if (alreadyInitialized) return

        this.domain.value = domain
        this.version.value = version
        this.onEmit = onEmit
        println("Analytics init for ${this.domain.value}")
    }

    fun setAffiliate(affiliate: String) {
        this.affiliate.value = affiliate
    }

    fun setABTestKey(abTestKey: String) {
        this.abTestKey.value = abTestKey
    }

    private suspend fun HttpClient.postEvent(event: PlausibleEvent) {
        println("will send event $event to $PLAUSIBLE_URL") // TODO: log and log levels task
        post(PLAUSIBLE_URL) {
            contentType(ContentType.Application.Json)
            setBody(event)
        }
    }

    internal fun buildAndSendPlausibleRequest(eventType: String, path: String, props: PlausibleProps) {
        domain.value?.let { domain ->
            val propsWithVersionAndDevice = props.copy(
                version = version.value,
                platform = getPlatform().name,
                abTestKey = abTestKey.value,
                affiliate = affiliate.value
            )
            val fullPath = "miam/$path" // TODO: path logic task
            val event = PlausibleEvent(
                eventType,
                fullPath,
                domain,
                propsWithVersionAndDevice
            )
            onEmit(event)
            coroutineScope.launch(coroutineHandler) {
                httpClient.postEvent(event)
            }
        } ?: println("Cannot send event without domain initialisation ${domain.value}")
    }
}
