package ai.mealz.analytics

import kotlin.js.JsExport
import kotlin.js.JsName

/*                 /!\ Must update `SharedAnalytics.ios.kt` too /!\                 */

@JsExport
@JsName("PlausibleProps")
data class PlausibleProps(
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
    val supplier_id: String? = null,
    // Point Of Sale
    val pos_id: String? = null,
    val pos_name: String? = null,
    // Search
    val search_term: String? = null,
    val stores_found_count: String? = null,
    // Meal Planner
    val budget: String? = null,
    val budget_user: String? = null,
    val budget_planner: String? = null,
    val recipe_count: String? = null,
    val guests: String? = null,
    val uses_count: String? = null,
    val time_passed: String? = null,
    // Global
    val client_sdk_version: String? = null,
    val analytics_sdk_version: String? = null,
    val platform: String? = null,
    val affiliate: String? = null,
    val abTestKey: String? = null
)
