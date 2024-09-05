package ai.mealz.analytics

import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("initSharedAnalytics")
fun initSharedAnalytics(supplierOrigin: String, version: String, onEmit: onEmitFunction) {
    SharedAnalytics.init(supplierOrigin, version, onEmit)
}
