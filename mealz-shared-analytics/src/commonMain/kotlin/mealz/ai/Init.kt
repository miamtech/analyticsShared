package ai.mealz.analytics

import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("initSharedAnalytics")
fun initSharedAnalytics(domain: String, version: String, onEmit: onEmitFunction) {
    SharedAnalytics.initSharedAnalytics(domain, version, onEmit)
}

@JsExport
@JsName("setABTestKey")
fun setABTestKey(abTestKey: String?) {
    SharedAnalytics.abTestKey = abTestKey
}

@JsExport
@JsName("setAffiliate")
fun setAffiliate(affiliate: String?) {
    SharedAnalytics.affiliate = affiliate
}
