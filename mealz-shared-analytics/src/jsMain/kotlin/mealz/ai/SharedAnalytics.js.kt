package ai.mealz.analytics

import kotlinx.browser.window
import org.w3c.fetch.RequestInit

actual object SharedAnalytics : AbstractSharedAnalytics() {

    override fun sendRequest(event: PlausibleEvent) {
        window.fetch(
            PLAUSIBLE_URL,
            init = RequestInit(
                method = "POST",
                body = JSON.stringify(event)
            )
        )
    }

    actual fun sendPlausibleRequest(plausiblePath: String, path: String, plausibleProps: PlausibleProps) {
        super.buildAndSendPlausibleRequest(plausiblePath, path, plausibleProps)
    }

    actual fun initSharedAnalytics(domain: String, version: String, abTestKey: String, affiliate: String, onEmit: onEmitFunction) {
        super.init(domain, version, abTestKey, affiliate, onEmit)
    }
}
