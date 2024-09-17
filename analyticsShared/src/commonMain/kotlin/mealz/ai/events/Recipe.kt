package ai.mealz.analytics.events

import ai.mealz.analytics.PlausibleDestinations
import ai.mealz.analytics.PlausibleProps
import ai.mealz.analytics.SharedAnalytics
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("sendRecipeShowEvent")
fun sendRecipeShowEvent(path: String, recipeId: String, categoryId: String?) {
    SharedAnalytics.sendPlausibleRequest(
        PlausibleDestinations.RECIPE_SHOW.plausiblePath,
        path,
        PlausibleProps(recipe_id = recipeId, category_id = categoryId)
    )
}

@JsExport
@JsName("sendRecipeDisplayEvent")
fun sendRecipeDisplayEvent(path: String, recipeId: String, categoryId: String?) {
    SharedAnalytics.sendPlausibleRequest(
        PlausibleDestinations.RECIPE_DISPLAY.plausiblePath,
        path,
        PlausibleProps(recipe_id = recipeId, category_id = categoryId)
    )
}

@JsExport
@JsName("sendRecipeAddEvent")
fun sendRecipeAddEvent(path: String, recipeId: String) {
    SharedAnalytics.sendPlausibleRequest(
        PlausibleDestinations.RECIPE_ADD.plausiblePath,
        path,
        PlausibleProps(recipe_id = recipeId)
    )
}

@JsExport
@JsName("sendRecipeRemoveEvent")
fun sendRecipeRemoveEvent(path: String, recipeId: String) {
    SharedAnalytics.sendPlausibleRequest(
        PlausibleDestinations.RECIPE_REMOVE.plausiblePath,
        path,
        PlausibleProps(recipe_id = recipeId)
    )
}

@JsExport
@JsName("sendRecipeChangeGuestsEvent")
fun sendRecipeChangeGuestsEvent(path: String, recipeId: String) {
    SharedAnalytics.sendPlausibleRequest(
        PlausibleDestinations.RECIPE_CHANGE_GUESTS.plausiblePath,
        path,
        PlausibleProps(recipe_id = recipeId)
    )
}

@JsExport
@JsName("sendRecipeLikeEvent")
fun sendRecipeLikeEvent(path: String, recipeId: String, categoryId: String?) {
    SharedAnalytics.sendPlausibleRequest(
        PlausibleDestinations.RECIPE_LIKE.plausiblePath,
        path,
        PlausibleProps(recipe_id = recipeId, category_id = categoryId)
    )
}

@JsExport
@JsName("sendRecipeUnlikeEvent")
fun sendRecipeUnlikeEvent(path: String, recipeId: String, categoryId: String?) {
    SharedAnalytics.sendPlausibleRequest(
        PlausibleDestinations.RECIPE_UNLIKE.plausiblePath,
        path,
        PlausibleProps(recipe_id = recipeId, category_id = categoryId)
    )
}

@JsExport
@JsName("sendRecipeSponsorEvent")
fun sendRecipeSponsorEvent(path: String, recipeId: String) {
    SharedAnalytics.sendPlausibleRequest(
        PlausibleDestinations.RECIPE_SPONSOR.plausiblePath,
        path,
        PlausibleProps(recipe_id = recipeId)
    )
}
