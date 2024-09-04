package mealz.ai.events

import mealz.ai.PlausibleDestinations
import mealz.ai.PlausibleProps
import mealz.ai.SharedAnalytics
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("sendSearchEvent")
fun sendSearchEvent(path: String, searchTerm: String) {
    SharedAnalytics.buildAndSendPlausibleRequest(
        PlausibleDestinations.SEARCH.plausiblePath,
        path,
        PlausibleProps(search_term = searchTerm)
    )
}
