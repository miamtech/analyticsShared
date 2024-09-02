package ai.mealz.analytics

expect fun getPlatform(): Platform

enum class Platform {
    ANDROID,
    IOS,
    WEB
}