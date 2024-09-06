package ai.mealz.analytics

import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.js.JsName

@Serializable
@JsExport
@JsName("PlausibleEvent")
data class PlausibleEvent(
    val name: String,
    val url: String,
    val domain: String,
    val props: PlausibleProps
)
