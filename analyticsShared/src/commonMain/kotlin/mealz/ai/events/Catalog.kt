package ai.mealz.analytics.events

import ai.mealz.analytics.PlausibleDestinations
import ai.mealz.analytics.PlausibleProps
import ai.mealz.analytics.SharedAnalytics
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("sendCategoryShowEvent")
fun sendCategoryShowEvent(path: String, categoryId: String) {
    SharedAnalytics.sendPlausibleRequest(
        PlausibleDestinations.CATEGORY_SHOW.plausiblePath,
        path,
        PlausibleProps(category_id = categoryId)
    )
}

@JsExport
@JsName("sendCategoryDisplayEvent")
fun sendCategoryDisplayEvent(path: String, categoryId: String) {
    SharedAnalytics.sendPlausibleRequest(
        PlausibleDestinations.CATEGORY_DISPLAY.plausiblePath,
        path,
        PlausibleProps(category_id = categoryId)
    )
}
