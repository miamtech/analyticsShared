package ai.mealz.analytics

typealias onEmitFunction = (PlausibleEvent) -> Unit

abstract class AbstractSharedAnalytics {
    private lateinit var onEmit: onEmitFunction
    private lateinit var domain: String
    private lateinit var analyticsSDKVersion: String
    private lateinit var clientSDKVersion: String

    internal var abTestKey: String? = null
    internal var affiliate: String? = null

    private val alreadyInitialized: Boolean
        get() = this::domain.isInitialized && this::clientSDKVersion.isInitialized && domain.isNotBlank() && clientSDKVersion.isNotBlank()

    fun init(domain: String, version: String, onEmit: onEmitFunction) {
        if (alreadyInitialized) return

        this.domain = domain
        this.analyticsSDKVersion = "##VERSION##"
        this.clientSDKVersion = version
        this.onEmit = onEmit
    }

    abstract fun sendRequest(event: PlausibleEvent)

    private fun validatePath(path: String) {
        // If the path does not contain "/miam/", it may just be the URL of the page (web) or
        // an empty path (mobile) so we assume it is valid
        if (!path.contains("/miam/")) return

        val validParts = "||miam|recipes|liked|categories|my-meals|detail|replace-item|sponsor|meals-planner|catalog|results|basket-preview|finalize|"
        var pathWithoutURL = path.substringAfter("/miam/")
        // Not using a list is the lightest way to do it when compiled in JS
        while (pathWithoutURL.isNotEmpty()) {
            // Find the index of the next '/' to separate path segments
            val nextSlashIndex = pathWithoutURL.indexOf('/').takeIf { it!=-1 } ?: pathWithoutURL.length
            val part = pathWithoutURL.substring(0, nextSlashIndex)
            // If the part is nor in the valid set of path segments or a number, throw an exception
            if (!validParts.contains("|$part|") && part.toIntOrNull() == null) {
                throw IllegalArgumentException("Invalid path : \"$path\". \"$part\" is not a valid path part.")
            }

            // Remove the processed part and move to the next segment if there's more path left
            pathWithoutURL = if (nextSlashIndex < pathWithoutURL.length) {
                pathWithoutURL.substring(nextSlashIndex + 1)
            } else "" // Path completely parsed
        }
    }

    internal fun buildAndSendPlausibleRequest(eventType: String, path: String, props: PlausibleProps) {
        if (!alreadyInitialized) return

        validatePath(path)
        val fullProps = props.copy(
            client_sdk_version = clientSDKVersion,
            analytics_sdk_version = analyticsSDKVersion,
            platform = getPlatform().name,
            abTestKey = abTestKey,
            affiliate = affiliate
        )
        val event = PlausibleEvent(
            eventType,
            path,
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
