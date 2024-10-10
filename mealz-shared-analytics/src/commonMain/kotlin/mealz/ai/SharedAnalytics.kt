package ai.mealz.analytics

expect object SharedAnalytics {
    fun initSharedAnalytics(domain: String, version: String, abTestKey: String, affiliate: String, onEmit: onEmitFunction)
    fun sendPlausibleRequest(plausiblePath: String, path: String, plausibleProps: PlausibleProps)
}
