# mealz-shared-analytics

Share analytics between Mealz projects

## Build

### Using Makefile

You can run `make` to build every platform, you also can specify `make <js|ios|android>`.

### Using gradle

#### Android aar

- Run `./gradlew assembleRelease`
- Output: `/mealz-shared-analytics/build/outputs/aar/mealz-shared-analytics-release.aar`

#### iOS Framework

- Run `./gradlew assembleXCFramework`
- Output: `/mealz-shared-analytics/build/XCFrameworks/release/mealz-shared-analytics.xcframework`

#### JS file

- Run `./gradlew jsBrowserProductionWebpack`
- Output: `/mealz-shared-analytics/build/dist/js/productionExecutable/main.js`

# MealzSharedAnalytics
