package mealz.ai

import kotlinx.serialization.Serializable

@Serializable
internal data class PlausibleEvent(
    val name: String,
    val url: String,
    val domain: String,
    val props: PlausibleProps
)