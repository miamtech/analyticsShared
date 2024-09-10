package ai.mealz.analytics

import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("initSharedAnalytics")
fun initSharedAnalytics(domain: String, version: String, onEmit: onEmitFunction) {
    SharedAnalytics.init(domain, version, onEmit)
}

@JsExport
@JsName("setAnalyticsABTestKey")
fun setABTestKey(abTestKey: String) {
    SharedAnalytics.setABTestKey(abTestKey)
}

@JsExport
@JsName("setAnalyticsAffiliate")
fun setAffiliate(affiliate: String) {
    SharedAnalytics.setAffiliate(affiliate)
}
