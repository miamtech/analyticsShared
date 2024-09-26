# mealz-shared-analytics

Share analytics between Mealz projects

## Build

### Using Makefile

You can run `make` to build every platform, you also can specify `make <js|ios|android>`.

### Using gradle

#### Android aar

- Run `./gradlew assembleRelease`
- Output: `/mealzSharedAnalytics/build/outputs/aar/mealzSharedAnalytics-release.aar`

#### iOS Framework

- Run `./gradlew assembleXCFramework`
- Output: `/mealzSharedAnalytics/build/XCFrameworks/release/mealzSharedAnalytics.xcframework`

#### JS file

- Run `./gradlew jsBrowserProductionWebpack`
- Output: `/mealzSharedAnalytics/build/dist/js/productionExecutable/mealzSharedAnalytics.js`

# MealzSharedAnalytics
