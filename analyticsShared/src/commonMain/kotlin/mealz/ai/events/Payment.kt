package mealz.ai.events

import mealz.ai.PlausibleDestinations
import mealz.ai.PlausibleProps
import mealz.ai.SharedAnalytics
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("sendPaymentStartedEvent")
fun sendPaymentStartedEvent(
    path: String,
    pointOfSaleName: String,
    pointOfSaleId: String,
    mealzAmount: String,
    totalAmount: String,
    basketId: String,
    recipeCount: String,
    mealzProducts: String,
    totalProducts: String,
    clientOrderId: String
) {
    SharedAnalytics.buildAndSendPlausibleRequest(
        PlausibleDestinations.PAYMENT_STARTED.plausiblePath,
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
@JsName("sendPaymentConfirmedEvent")
fun sendPaymentConfirmedEvent(
    path: String,
    pointOfSaleName: String,
    pointOfSaleId: String,
    mealzAmount: String,
    totalAmount: String,
    basketId: String,
    recipeCount: String,
    mealzProducts: String,
    totalProducts: String,
    clientOrderId: String
) {
    SharedAnalytics.buildAndSendPlausibleRequest(
        PlausibleDestinations.PAYMENT_CONFIRMED.plausiblePath,
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
