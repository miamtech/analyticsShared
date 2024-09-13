package ai.mealz.analytics.events

import ai.mealz.analytics.PlausibleDestinations
import ai.mealz.analytics.PlausibleProps
import ai.mealz.analytics.SharedAnalytics
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("sendSearchEvent")
fun sendSearchEvent(path: String, searchTerm: String) {
    SharedAnalytics.sendPlausibleRequest(
        PlausibleDestinations.SEARCH.plausiblePath,
        path,
        PlausibleProps(search_term = searchTerm)
    )
}
