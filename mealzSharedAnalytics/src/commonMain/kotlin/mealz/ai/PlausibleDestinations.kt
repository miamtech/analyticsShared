package ai.mealz.analytics

internal enum class PlausibleDestinations(val plausiblePath: String) {
    // -------------------------------- PAGE VIEW ---------------------------------------------
    PAGEVIEW("pageview"),

    // ---------------------------------- SEARCH ----------------------------------------------
    SEARCH("search"),

    // ---------------------------------- RECIPE ----------------------------------------------
    RECIPE_SHOW("recipe.show"),
    RECIPE_DISPLAY("recipe.display"),
    RECIPE_ADD("recipe.add"),
    RECIPE_REMOVE("recipe.remove"),
    RECIPE_CHANGE_GUESTS("recipe.change-guests"),
    RECIPE_LIKE("recipe.like"),
    RECIPE_UNLIKE("recipe.unlike"),
    RECIPE_SPONSOR("recipe.sponsor"),

    // ---------------------------------- CATALOG ----------------------------------------------
    CATEGORY_SHOW("category.show"),
    CATEGORY_DISPLAY("category.display"),

    // ---------------------------------- PRODUCT ----------------------------------------------
    BASKET_ENTRY_ADD("entry.add"),
    BASKET_ENTRY_DELETE("entry.delete"),
    BASKET_ENTRY_REPLACE("entry.replace"),
    BASKET_ENTRY_CHANGE_QUANTITY("entry.change-quantity"),

    // ---------------------------------- PAYMENT ----------------------------------------------
    PAYMENT_STARTED("payment.started"),
    PAYMENT_CONFIRMED("payment.confirmed"),

    // ---------------------------------- BASKET ----------------------------------------------
    BASKET_CONFIRMED("basket.confirmed"),
    BASKET_TRANSFER("basket.transfer"),

    // ------------------------------- MEAL PLANNER --------------------------------------------
    PLANNER_STARTED("planner.started"),
    PLANNER_RECIPE_DELETED("planner.recipe.delete"),
    PLANNER_CONFIRM("planner.confirm"),
    PLANNER_FINALIZE("planner.finalize"),

    // ------------------------------- STORE LOCATOR -------------------------------------------
    POINT_OF_SALE_SELECTED("pos.selected"),
    SEARCH_STORE("search.store"),
}