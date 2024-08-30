package mealz.ai

import kotlinx.serialization.Serializable

@Serializable
internal data class PlausibleProps(
    val recipe_id: String? = null,
    val category_id: String? = null,
    val entry_name: String? = null,
    val basket_id: String? = null,
    val miam_amount: Float? = null,
    val total_amount: String? = null,
    val pos_id: String? = null,
    val pos_total_amount: String? = null,
    val pos_name: String? = null,
    val search_term: String? = null,
    val uses_count: String? = null,
    val time_passed: String? = null,
    val budget_user: String? = null,
    val budget_planner: String? = null,
    val recipe_count: String? = null,
    val query: String? = null,
    val guests: String? = null,
    val item_id: String? = null,
    val item_ean: String? = null,
    val user_preference: String? = null,
    val old_item_id: String? = null,
    val old_item_ean: String? = null,
    val new_item_id: String? = null,
    val new_item_ean: String? = null,
    val recipe_item_id: String? = null,
    val diff: String? = null,
    val from_miam: Boolean? = null,
    val miam_products: Int? = null,
    val total_products: Int? = null,
    val product_quantity: Int? = null,
    val client_order_id: String? = null,
    val version: String? = null,
    val device: String? = null
)