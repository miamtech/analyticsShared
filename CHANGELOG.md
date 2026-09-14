## 4.15.0
[FEA] Added `product.show` event with parameters `entry_name`, `item_id`, `ext_item_id`, `item_ean`, `product_base_price`, `recipe_id`

## 4.14.0
[FEA] Added optional parameter `product_base_price` for `entry.added` and `entry.deleted` events
[FIX] Use `keepalive` to send analytics events and avoid request cancellation

## 4.13.1
[FIX] Analytics `sendEvent` / path-journey validation no longer throw; invalid events are logged and skipped so host apps are not interrupted

## 4.13.0
[FEA] Added optional parameter `category_id` from `recipe.add`

## 4.12.0
[FEA] Added valid journey `meals-space-header`

## 4.11.0
[FEA] Added parameter `guests` for `planner.mode.select` event

## 4.10.0
[FEA] Added valid journey `meals-space-category-search`
[FEA] Added parameters `add_source`, `mode` for `entry.added` event
[FEA] Added parameters `total_price`, `total_products` for `entry.add-all` event

## 4.9.0
[FEA] Added parameter `recipe_source` for `recipe.add` event

## 4.8.0
[FEA] Added parameters for `planner.recipe.add`, `planner.finalize`and `recipe.add` (`mode` and `recipe_source`)

## 4.7.2
[FEA] Added valid journey `meals-planner-dashboard`

## 4.7.1
[FEA] Added new valid journey values
[FEA] Added `planner.mode.select` event with `mode`parameter

## 4.7.0
[FEA] Add environment parameter to `initSharedAnalytics` to send events to BigQuery UAT or PROD.

## 4.6.0
[FEA] Added `planner.onboarding.display` event with `type` and `step` parameters
[FEA] Added `planner.onboarding.start` event with `type` parameter
[FEA] Added `planner.onboarding.skip` event with `type` parameter
[FEA] Added `planner.onboarding.next` event with `type` and `step` parameters
[FEA] Added `planner.onboarding.understood` event with `type` parameter
[FEA] Added `planner.onboarding.close` event with `type` and `step` parameters
[FEA] Added `planner.help.question-mark` event with `from` parameter
[FEA] Added `planner.help.display` event with `step` parameter
[FEA] Added `planner.help.next` event with `step` parameter
[FEA] Added `planner.help.previous` event with `step` parameter
[FEA] Added `planner.help.understood` event
[FEA] Added `planner.help.close` event with `step` parameter

## 4.5.0
[FEA] Events now send to plausible and mealz-analytics
[FIX] Fix a problem where coroutines would stop if a request crashed in Android and iOS, meaning no other request could be sent.

## 4.4.1
[FIX] Force release

## 4.4.0
[FEA] Added `supplier-selector.display` event
[FEA] Added `supplier-selector.back` event  
[FEA] Added `supplier-selector.close` event
[FEA] Added `supplier-selector.select` event
[FEA] Added "supplier-selector" to `VALID_PATH_PARTS`

## 4.3.1
[FEA] Added `planner.item.replaced` event

## 4.3.0
[FEA] Added `planner.reset` event
[FEA] Added `planner.recipe.add` event
[FEA] Added `planner.recipe.swap` event
[FEA] Added `planner.recipe.catalog-prompt` event
[FEA] Added `planner.suggestion.show` event
[FEA] Added `planner.item.delete` event
[FEA] Added `planner.item.add` event
[FEA] Added `planner.item.replace` event
[FEA] Added `dashboard`, `current`, `history` to `VALID_PATH_PARTS`
[FEA] Updated `PLANNER_STARTED` parameters
[FEA] Updated `PLANNER_FINALIZE` parameters
[FIX] Add ProGuard rule to keep object directly used for analytics

## 4.2.0
[FEA] Added `basket.show` event
[FEA] Added `search.results` event
[FEA] Added "filter" & "search" to `path`

## 4.1.1
[FIX] `entry.replace` props "item_id", "ext_item_id" and "item_ean" are now optional

## 4.1.0
[FEA] Added "status" to `entry.replace` optional props
[FEA] Added "guests" to `recipe.change-guests` optional props
[FEA] Added "list-scan" to valid path parts

## 4.0.0
[BRK][FIX] Fix `entry.replace` event had the wrong props
[BRK][FIX] Fix `item-selector.back` and `item-selector.close` event had the wrong props

## 3.0.0
[BRK][FEA] Replaced PlausibleProps by `MutableMap<String, String?>` in mobile and `{ [key: string]: string }` in js
[BRK][FEA] Replaced all event sending functions by generic EventSender.sendEvent
[BRK][FEA] Renamed pos.* events to locator.*
[BRK][FEA] Updated valid path parts
[BRK][FEA] Added a journey property to all events
[FEA] Add new events
[FEA] Add PlatformList wrapper so that we can use native lists in JS
[FEA] Add PlatformMap wrapper so that we can use native objects in JS
[FIX] CI/CD can now recognize alpha versions

## 2.0.0
[BRK][FIX] Rename search.store in pos.search
[FEA] Add recipe.close, recipe.continue, locator.close, onboarding.action, onboarding.close events
[FEA] Add supplier_name, steps_completed props

## 1.0.0
[FEA] Create project
