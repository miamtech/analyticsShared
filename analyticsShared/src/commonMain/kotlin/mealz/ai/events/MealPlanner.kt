package mealz.ai.events

import mealz.ai.PlausibleDestinations
import mealz.ai.PlausibleProps
import mealz.ai.SharedAnalytics
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("sendPlannerStartedEvent")
fun sendPlannerStartedEvent(path: String, budget: String, guests: String, recipeCount: String) {
    SharedAnalytics.buildAndSendPlausibleRequest(
        PlausibleDestinations.PLANNER_STARTED.plausiblePath,
        path,
        PlausibleProps(budget = budget, guests = guests, recipe_count = recipeCount)
    )
}

@JsExport
@JsName("sendPlannerRecipeDeletedEvent")
fun sendPlannerRecipeDeletedEvent(path: String, recipeId: String) {
    SharedAnalytics.buildAndSendPlausibleRequest(
        PlausibleDestinations.PLANNER_RECIPE_DELETED.plausiblePath,
        path,
        PlausibleProps(recipe_id = recipeId)
    )
}

@JsExport
@JsName("sendPlannerConfirmEvent")
fun sendPlannerConfirmEvent(
    path: String,
    budgetUser: String,
    budgetPlanner: String,
    recipeCount: String,
    guests: String,
    usesCount: String,
    timePassed: String
) {
    SharedAnalytics.buildAndSendPlausibleRequest(
        PlausibleDestinations.PLANNER_CONFIRM.plausiblePath,
        path,
        PlausibleProps(
            budget_user = budgetUser,
            budget_planner = budgetPlanner,
            recipe_count = recipeCount,
            guests = guests,
            uses_count = usesCount,
            time_passed = timePassed
        )
    )
}

@JsExport
@JsName("sendPlannerFinalizeEvent")
fun sendPlannerFinalizeEvent(
    path: String,
    budgetUser: String,
    budgetPlanner: String,
    recipeCount: String,
    guests: String
) {
    SharedAnalytics.buildAndSendPlausibleRequest(
        PlausibleDestinations.PLANNER_FINALIZE.plausiblePath,
        path,
        PlausibleProps(
            budget_user = budgetUser,
            budget_planner = budgetPlanner,
            recipe_count = recipeCount,
            guests = guests
        )
    )
}
