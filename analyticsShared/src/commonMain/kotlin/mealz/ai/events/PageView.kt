package ai.mealz.analytics.events

import ai.mealz.analytics.PlausibleDestinations
import ai.mealz.analytics.PlausibleProps
import ai.mealz.analytics.SharedAnalytics
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("sendPageViewEvent")
fun sendPageViewEvent(path: String) {
    SharedAnalytics.sendPlausibleRequest(
        PlausibleDestinations.PAGEVIEW.plausiblePath,
        path,
        PlausibleProps()
    )
}
