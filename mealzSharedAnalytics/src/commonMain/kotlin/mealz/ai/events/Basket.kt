package ai.mealz.analytics.events

import ai.mealz.analytics.PlausibleDestinations
import ai.mealz.analytics.PlausibleProps
import ai.mealz.analytics.SharedAnalytics
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("sendBasketConfirmedEvent")
fun sendBasketConfirmedEvent(
    path: String,
    pointOfSaleName: String,
    pointOfSaleId: String,
    mealzAmount: String,
    totalAmount: String,
    basketId: String,
    recipeCount: String,
    mealzProducts: String,
    totalProducts: String?,
    clientOrderId: String?
) {
    SharedAnalytics.sendPlausibleRequest(
        PlausibleDestinations.BASKET_CONFIRMED.plausiblePath,
        path,
        PlausibleProps(
            basket_id = basketId,
            miam_amount = mealzAmount,
            total_amount = totalAmount,
            pos_id = pointOfSaleId,
            pos_name = pointOfSaleName,
            recipe_count = recipeCount,
            miam_products = mealzProducts,
            total_products = totalProducts,
            client_order_id = clientOrderId
        )
    )
}

@JsExport
@JsName("sendBasketTransferEvent")
fun sendBasketTransferEvent(
    path: String,
    pointOfSaleName: String,
    pointOfSaleId: String,
    mealzAmount: String,
    basketId: String,
    supplierId: String
) {
    SharedAnalytics.sendPlausibleRequest(
        PlausibleDestinations.BASKET_TRANSFER.plausiblePath,
        path,
        PlausibleProps(
            basket_id = basketId,
            miam_amount = mealzAmount,
            pos_id = pointOfSaleId,
            pos_name = pointOfSaleName,
            supplier_id = supplierId
        )
    )
}
