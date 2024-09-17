package ai.mealz.analytics

import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("initSharedAnalytics")
fun initSharedAnalytics(domain: String, version: String, abTestKey: String, affiliate: String, onEmit: onEmitFunction) {
    SharedAnalytics.initSharedAnalytics(domain, version, abTestKey, affiliate, onEmit)
}
