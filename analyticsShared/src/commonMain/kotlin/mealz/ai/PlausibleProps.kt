package mealz.ai

import kotlinx.serialization.Serializable

@Serializable
internal data class PlausibleProps(
    // Recipe
    val recipe_id: String? = null,
    // Catalog
    val category_id: String? = null,
    // Entry / Item / Product
    val entry_name: String? = null,
    val item_id: String? = null,
    val ext_item_id: String? = null,
    val item_ean: String? = null,
    val old_item_id: String? = null,
    val old_item_ext_id: String? = null,
    val old_item_ean: String? = null,
    val new_item_id: String? = null,
    val new_item_ext_id: String? = null,
    val new_item_ean: String? = null,
    val product_quantity: String? = null,
    // Basket / Payment
    val basket_id: String? = null,
    val miam_amount: String? = null,
    val total_amount: String? = null,
    val miam_products: String? = null,
    val total_products: String? = null,
    val client_order_id: String? = null,
    // Point Of Sale
    val pos_id: String? = null,
    val pos_name: String? = null,
    // Search
    val search_term: String? = null,
    // Meal Planner
    val budget: String? = null,
    val budget_user: String? = null,
    val budget_planner: String? = null,
    val recipe_count: String? = null,
    val guests: String? = null,
    val uses_count: String? = null,
    val time_passed: String? = null,
    // Global
    val version: String? = null,
    val device: String? = null
)
