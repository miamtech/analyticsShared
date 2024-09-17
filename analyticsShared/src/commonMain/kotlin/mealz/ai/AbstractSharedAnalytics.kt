package ai.mealz.analytics

typealias onEmitFunction = (PlausibleEvent) -> Unit

abstract class AbstractSharedAnalytics {
    private lateinit var onEmit: onEmitFunction
    private lateinit var domain: String
    private lateinit var version: String
    private lateinit var abTestKey: String
    private lateinit var affiliate: String

    private val alreadyInitialized: Boolean
        get() = this::domain.isInitialized && this::version.isInitialized && domain.isNotBlank() && version.isNotBlank()

    fun init(domain: String, version: String, abTestKey: String, affiliate: String, onEmit: onEmitFunction) {
        if (alreadyInitialized) return

        this.domain = domain
        this.version = version
        this.abTestKey = abTestKey
        this.affiliate = affiliate
        this.onEmit = onEmit
    }

    abstract fun sendRequest(event: PlausibleEvent);

    internal fun buildAndSendPlausibleRequest(eventType: String, path: String, props: PlausibleProps) {
        if (!alreadyInitialized) return

        val fullProps = props.copy(
            version = version,
            platform = getPlatform().name,
            abTestKey = abTestKey,
            affiliate = affiliate
        )
        val fullPath = if (path.isNotBlank()) "miam$path" else ""
        val event = PlausibleEvent(
            eventType,
            fullPath,
            domain,
            fullProps
        )
        onEmit(event)
        sendRequest(event)
    }

    companion object {
        internal const val PLAUSIBLE_URL: String = "https://plausible.io/api/event"
    }
}
