package mealz.ai

internal enum class PlausibleDestinations(val plausiblePath: String) {
    // -------------------------------- PAGE VIEW ---------------------------------------------
    PAGEVIEW("pageview"),
    // ---------------------------------- RECIPE ----------------------------------------------
    RECIPE_SHOW("recipe.show"),
    RECIPE_DISPLAY("recipe.display"),
    RECIPE_ADD("recipe.add"),
    RECIPE_REMOVE("recipe.remove"),
    RECIPE_RESET("recipe.reset"),
    RECIPE_CHANGE_GUESTS("recipe.change-guests"),
    RECIPE_LIKE("recipe.like"),
    RECIPE_UNLIKE("recipe.unlike"),
    RECIPE_SPONSOR("recipe.sponsor"),
    // ---------------------------------- CATALOG ----------------------------------------------
    CATALOG_SEARCH("search"),
    CATALOG_SHOW("catalog.show"),
    CATALOG_DISPLAY("catalog.display"),
    // ---------------------------------- PRODUCT ----------------------------------------------
    BASKET_ENTRY_ADD("entry.add"),
    BASKET_ENTRY_DELETE("entry.delete"),
    BASKET_ENTRY_REPLACE("entry.replace"),
    BASKET_ENTRY_CHANGE_QUANTITY("entry.change-quantity"),
    // ---------------------------------- PAYMENT ----------------------------------------------
    PAYMENT_STARTED("payment.started"),
    PAYMENT_FINISHED("payment.confirmed"),
    // ---------------------------------- BASKET ----------------------------------------------
    BASKET_CONFIRMED("basket.confirmed"),
    // ---------------------------------- BASKET ----------------------------------------------
    PLANNER_STARTED("planner.started"),
    PLANNER_RECIPE_DELETED("planner.recipe.delete"),
    PLANNER_CONFIRM("planner.confirm"),
    PLANNER_FINALIZE("planner.finalize"),
    // ---------------------------------- PAYMENT ----------------------------------------------
    POINT_OF_SALE_SELECTED("pos.selected"),
}