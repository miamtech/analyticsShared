package ai.mealz.analytics

expect object SharedAnalytics {
    internal var abTestKey: String?
    internal var affiliate: String?
    fun initSharedAnalytics(domain: String, version: String, onEmit: onEmitFunction)
    fun sendPlausibleRequest(plausiblePath: String, path: String, plausibleProps: PlausibleProps)
}
