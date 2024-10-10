package ai.mealz.analytics.events

import ai.mealz.analytics.PlausibleDestinations
import ai.mealz.analytics.PlausibleProps
import ai.mealz.analytics.SharedAnalytics
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("sendEntryAddEvent")
fun sendEntryAddEvent(
    path: String,
    entryName: String,
    itemId: String,
    extItemId: String,
    itemEAN: String,
    productQuantity: String,
    recipeId: String?
) {
    SharedAnalytics.sendPlausibleRequest(
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
    itemId: String,
    extItemId: String,
    itemEAN: String,
    productQuantity: String,
    recipeId: String?
) {
    SharedAnalytics.sendPlausibleRequest(
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
    newItemId: String,
    newItemExtId: String,
    newItemEAN: String,
    productQuantity: String,
    oldItemId: String?,
    oldItemExtId: String?,
    oldItemEAN: String?,
    searchTerm: String?,
    recipeId: String?
) {
    SharedAnalytics.sendPlausibleRequest(
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
    itemId: String,
    extItemId: String,
    itemEAN: String,
    productQuantity: String,
    recipeId: String?
) {
    SharedAnalytics.sendPlausibleRequest(
        PlausibleDestinations.BASKET_ENTRY_CHANGE_QUANTITY.plausiblePath,
        path,
        PlausibleProps(
            entry_name = entryName,
            recipe_id = recipeId,
            item_id = itemId,
            ext_item_id = extItemId,
            product_quantity = productQuantity,
            item_ean = itemEAN
        )
    )
}
