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
        this.buildAndSendPlausibleRequest(plausiblePath, path, plausibleProps)
    }

    actual fun initSharedAnalytics(domain: String, version: String, onEmit: onEmitFunction) {
        this.init(domain, version, onEmit)
    }
}
