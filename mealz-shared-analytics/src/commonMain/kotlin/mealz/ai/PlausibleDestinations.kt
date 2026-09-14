package ai.mealz.analytics

internal enum class PlausibleDestinations(val plausiblePath: String) {
    // -------------------------------- PAGE VIEW ---------------------------------------------
    PAGEVIEW("pageview"),

    // ---------------------------------- SEARCH ----------------------------------------------
    SEARCH("search"),
    SEARCH_RESULTS("search.results"),

    // ---------------------------------- RECIPE ----------------------------------------------
    RECIPE_SHOW("recipe.show"),
    RECIPE_DISPLAY("recipe.display"),
    RECIPE_COOKING_DISPLAY("recipe.cooking.display"),
    RECIPE_SHOPPING_DISPLAY("recipe.shopping.display"),
    RECIPE_ADD("recipe.add"),
    RECIPE_REMOVE("recipe.remove"),
    RECIPE_CHANGE_GUESTS("recipe.change-guests"),
    RECIPE_LIKE("recipe.like"),
    RECIPE_UNLIKE("recipe.unlike"),
    RECIPE_CLOSE("recipe.close"),
    RECIPE_CONTINUE("recipe.continue"),

    // ------------------------------- RECIPE ORDERED ------------------------------------------
    RECIPE_ORDERED_DISPLAY("recipe.ordered.display"),
    RECIPE_ORDERED_CLOSE("recipe.ordered.close"),

    // ---------------------------------- SPONSOR ----------------------------------------------
    SPONSOR_DISPLAY("sponsor.display"),
    SPONSOR_BACK("sponsor.back"),
    SPONSOR_CLOSE("sponsor.close"),

    // ------------------------------------ HOME -----------------------------------------------
    HOME_DISPLAY("home.display"),

    // --------------------------------- BREADCRUMB --------------------------------------------
    BREADCRUMB_SELECT("breadcrumb.select"),

    // ---------------------------------- CATEGORY ---------------------------------------------
    CATEGORY_SHOW("category.show"),
    CATEGORY_DISPLAY("category.display"),
    CATEGORY_BACK("category.back"),

    // ----------------------------------- LIKED -----------------------------------------------
    LIKED_DISPLAY("liked.display"),
    LIKED_BACK("liked.back"),
    LIKED_SEARCH("liked.search"),

    // ---------------------------------- ORDERS -----------------------------------------------
    ORDERS_DISPLAY("orders.display"),
    ORDERS_BACK("orders.back"),
    ORDERS_SEARCH("orders.search"),

    // ----------------------------------- ORDER -----------------------------------------------
    ORDER_DISPLAY("order.display"),
    ORDER_BACK("order.back"),
    ORDER_CLOSE("order.close"),

    // -------------------------------- PREFERENCES --------------------------------------------
    PREFERENCES_DISPLAY("preferences.display"),
    PREFERENCES_CLOSE("preferences.close"),
    PREFERENCES_RESET("preferences.reset"),
    PREFERENCES_SAVE("preferences.save"),
    PREFERENCES_GUESTS("preferences.guests"),
    PREFERENCES_TAG_SELECTED("preferences.tag.selected"),
    PREFERENCES_SEARCH("preferences.search"),

    // ---------------------------------- PRODUCT ----------------------------------------------
    ENTRY_ADD("entry.add"),
    ENTRY_ADDED("entry.added"),
    ENTRY_ADD_ALL("entry.add-all"),
    ENTRY_ADD_ALL_AGAIN("entry.add-all-again"),
    ENTRY_DELETE("entry.delete"),
    ENTRY_DELETED("entry.deleted"),
    ENTRY_REPLACE("entry.replace"),
    ENTRY_REPLACED("entry.replaced"),
    ENTRY_CHANGE_QUANTITY("entry.change-quantity"),
    ENTRY_IGNORE("entry.ignore"),
    PRODUCT_SHOW("product.show"),

    // ------------------------------- ITEM SELECTOR -------------------------------------------
    ITEM_SELECTOR_BACK("item-selector.back"),
    ITEM_SELECTOR_CLOSE("item-selector.close"),
    ITEM_SELECTOR_SEARCH("item-selector.search"),

    // ---------------------------------- PAYMENT ----------------------------------------------
    PAYMENT_STARTED("payment.started"),
    PAYMENT_CONFIRMED("payment.confirmed"),

    // ---------------------------------- BASKET -----------------------------------------------
    BASKET_DISPLAY("basket.display"),
    BASKET_SHOW("basket.show"),
    BASKET_RECIPES_DISPLAY("basket.recipes.display"),
    BASKET_PRODUCTS_DISPLAY("basket.products.display"),
    BASKET_CLOSE("basket.close"),
    BASKET_ENTRY_ADD("basket.entry.add"),
    BASKET_CONFIRMED("basket.confirmed"),
    BASKET_TRANSFER("basket.transfer"),

    // ------------------------------- MEAL PLANNER --------------------------------------------
    PLANNER_STARTED("planner.started"),
    PLANNER_RESET("planner.reset"),
    PLANNER_RECIPE_ADDED("planner.recipe.add"),
    PLANNER_RECIPE_DELETED("planner.recipe.delete"),
    PLANNER_RECIPE_SWAPPED("planner.recipe.swap"),
    PLANNER_RECIPE_CATALOG_PROMPT("planner.recipe.catalog-prompt"),
    PLANNER_SUGGESTION_SHOW("planner.suggestion.show"),
    PLANNER_ITEM_DELETED("planner.item.delete"),
    PLANNER_ITEM_ADDED("planner.item.add"),
    PLANNER_ITEM_REPLACE("planner.item.replace"),
    PLANNER_ITEM_REPLACED("planner.item.replaced"),
    PLANNER_CONFIRM("planner.confirm"),
    PLANNER_FINALIZE("planner.finalize"),
    PLANNER_MODE_SELECT("planner.mode.select"),

    // ------------------------------- SUPPLIER SELECTOR -------------------------------------------
    SUPPLIER_SELECTOR_DISPLAY("supplier-selector.display"),
    SUPPLIER_SELECTOR_BACK("supplier-selector.back"),
    SUPPLIER_SELECTOR_CLOSE("supplier-selector.close"),
    SUPPLIER_SELECTOR_SELECT("supplier-selector.select"),

    // ------------------------------- STORE LOCATOR -------------------------------------------
    LOCATOR_DISPLAY("locator.display"),
    LOCATOR_MAP_DISPLAY("locator.map.display"),
    LOCATOR_LIST_DISPLAY("locator.list.display"),
    LOCATOR_BACK("locator.back"),
    LOCATOR_CLOSE("locator.close"),
    LOCATOR_SEARCH("locator.search"),
    LOCATOR_FILTER("locator.filter"),
    LOCATOR_SELECT("locator.select"),

    // -------------------------------- ONBOARDING ---------------------------------------------
    ONBOARDING_ACTION("onboarding.action"),
    ONBOARDING_CLOSE("onboarding.close"),

    // -------------------------------- PLANNER ONBOARDING ---------------------------
    PLANNER_ONBOARDING_DISPLAY("planner.onboarding.display"),
    PLANNER_ONBOARDING_START_CLICK("planner.onboarding.start"),
    PLANNER_ONBOARDING_SKIP_CLICK("planner.onboarding.skip"),
    PLANNER_PONBOARDING_NEXT_CLICK("planner.onboarding.next"),
    PLANNER_ONBOARDING_UNDERSTOOD_CLICK("planner.onboarding.understood"),
    PLANNER_ONBOARDING_CLOSE_CLICK("planner.onboarding.close"),

    // -------------------------------- PLANNER HELP MODAL ----------------------------------------
    PLANNER_HELP_QUESTION_MARK_CLICK("planner.help.question-mark"),
    PLANNER_HELP_DISPLAY("planner.help.display"),
    PLANNER_HELP_NEXT_CLICK("planner.help.next"),
    PLANNER_HELP_PREVIOUS_CLICK("planner.help.previous"),
    PLANNER_HELP_UNDERSTOOD_CLICK("planner.help.understood"),
    PLANNER_HELP_CLOSE_CLICK("planner.help.close"),
}
