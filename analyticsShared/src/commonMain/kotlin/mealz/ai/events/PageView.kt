package mealz.ai.events

import mealz.ai.PlausibleDestinations
import mealz.ai.PlausibleProps
import mealz.ai.SharedAnalytics
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("sendPageViewEvent")
fun sendPageViewEvent(path: String) {
    SharedAnalytics.buildAndSendPlausibleRequest(
        PlausibleDestinations.PAGEVIEW.plausiblePath,
        path,
        PlausibleProps()
    )
}
