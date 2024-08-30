package mealz.ai.events

import mealz.ai.PlausibleDestinations
import mealz.ai.PlausibleProps
import mealz.ai.SharedAnalytics
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("sendRecipeShowEvent")
public fun sendRecipeShowEvent(recipeId: String, catalogId: String?, path: String) {
    SharedAnalytics.buildAndSendPlausibleRequest(
        PlausibleDestinations.RECIPE_SHOW.plausiblePath,
        path,
        PlausibleProps(recipe_id = recipeId, category_id = catalogId)
    )
}

@JsExport
@JsName("sendRecipeDisplayEvent")
public fun sendRecipeDisplayEvent(recipeId: String, catalogId: String?, path: String) {
    SharedAnalytics.buildAndSendPlausibleRequest(
        PlausibleDestinations.RECIPE_DISPLAY.plausiblePath,
        path,
        PlausibleProps(recipe_id = recipeId, category_id = catalogId)
    )
}