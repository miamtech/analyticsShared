package mealz.ai.events

import mealz.ai.PlausibleDestinations
import mealz.ai.PlausibleProps
import mealz.ai.SharedAnalytics
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("sendEntryAddEvent")
fun sendEntryAddEvent(
    path: String,
    entryName: String,
    recipeId: String,
    itemId: String,
    extItemId: String,
    itemEAN: String,
    productQuantity: String
) {
    SharedAnalytics.buildAndSendPlausibleRequest(
        PlausibleDestinations.BASKET_ENTRY_ADD.plausiblePath,
        path,
        PlausibleProps(
            entry_name = entryName,
            recipe_id = recipeId,
            item_id = itemId,
            ext_item_id = extItemId,
            item_ean = itemEAN,
            product_quantity = productQuantity
        )
    )
}

@JsExport
@JsName("sendEntryDeleteEvent")
fun sendEntryDeleteEvent(
    path: String,
    entryName: String,
    recipeId: String,
    itemId: String,
    extItemId: String,
    itemEAN: String,
    productQuantity: String
) {
    SharedAnalytics.buildAndSendPlausibleRequest(
        PlausibleDestinations.BASKET_ENTRY_DELETE.plausiblePath,
        path,
        PlausibleProps(
            entry_name = entryName,
            recipe_id = recipeId,
            item_id = itemId,
            ext_item_id = extItemId,
            item_ean = itemEAN,
            product_quantity = productQuantity
        )
    )
}

@JsExport
@JsName("sendEntryReplaceEvent")
fun sendEntryReplaceEvent(
    path: String,
    entryName: String,
    recipeId: String,
    newItemId: String,
    newItemExtId: String,
    newItemEAN: String,
    oldItemId: String,
    oldItemExtId: String,
    oldItemEAN: String,
    productQuantity: String,
    searchTerm: String?
) {
    SharedAnalytics.buildAndSendPlausibleRequest(
        PlausibleDestinations.BASKET_ENTRY_REPLACE.plausiblePath,
        path,
        PlausibleProps(
            entry_name = entryName,
            recipe_id = recipeId,
            new_item_id = newItemId,
            new_item_ext_id = newItemExtId,
            new_item_ean = newItemEAN,
            old_item_id = oldItemId,
            old_item_ext_id = oldItemExtId,
            old_item_ean = oldItemEAN,
            product_quantity = productQuantity,
            search_term = searchTerm
        )
    )
}

@JsExport
@JsName("sendEntryChangeQuantityEvent")
fun sendEntryChangeQuantityEvent(
    path: String,
    entryName: String,
    recipeId: String,
    itemId: String,
    extItemId: String,
    itemEAN: String
) {
    SharedAnalytics.buildAndSendPlausibleRequest(
        PlausibleDestinations.BASKET_ENTRY_CHANGE_QUANTITY.plausiblePath,
        path,
        PlausibleProps(
            entry_name = entryName,
            recipe_id = recipeId,
            item_id = itemId,
            ext_item_id = extItemId,
            item_ean = itemEAN
        )
    )
}
