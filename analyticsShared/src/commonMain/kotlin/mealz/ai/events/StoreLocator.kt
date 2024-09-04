package mealz.ai.events

import mealz.ai.PlausibleDestinations
import mealz.ai.PlausibleProps
import mealz.ai.SharedAnalytics
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("sendPointOfSaleSelectedEvent")
fun sendPointOfSaleSelectedEvent(path: String, pointOfSaleName: String, pointOfSaleId: String) {
    SharedAnalytics.buildAndSendPlausibleRequest(
        PlausibleDestinations.POINT_OF_SALE_SELECTED.plausiblePath,
        path,
        PlausibleProps(pos_id = pointOfSaleId, pos_name = pointOfSaleName)
    )
}
