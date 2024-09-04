package mealz.ai.events

import mealz.ai.PlausibleDestinations
import mealz.ai.PlausibleProps
import mealz.ai.SharedAnalytics
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("sendCategoryShowEvent")
fun sendCategoryShowEvent(path: String, categoryId: String) {
    SharedAnalytics.buildAndSendPlausibleRequest(
        PlausibleDestinations.CATEGORY_SHOW.plausiblePath,
        path,
        PlausibleProps(category_id = categoryId)
    )
}

@JsExport
@JsName("sendCategoryDisplayEvent")
fun sendCategoryDisplayEvent(path: String, categoryId: String) {
    SharedAnalytics.buildAndSendPlausibleRequest(
        PlausibleDestinations.CATEGORY_DISPLAY.plausiblePath,
        path,
        PlausibleProps(category_id = categoryId)
    )
}
