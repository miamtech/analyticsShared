package ai.mealz.analytics.handler

class LogHandler {
    enum class LogLevels {
        ALL_LOGS,
        ERRORS_ONLY,
        ERRORS_AND_WARNS,
        NO_LOGS
    }

    companion object {
        var keepLog: Boolean = false
        var stackTrace: String = ""

        // Default to ERRORS_AND_WARNS, can be changed as needed
        var logLevel: LogLevels = LogLevels.ERRORS_AND_WARNS


        var debug: (String) -> Unit = fun(msg: String) {
            if (logLevel==LogLevels.ALL_LOGS) printMessage(msg, "DEBUG")
        }

        var info: (String) -> Unit = fun(msg: String) {
            if (logLevel==LogLevels.ALL_LOGS) printMessage(msg, "INFO")
        }

        var warn: (String) -> Unit = fun(msg: String) {
            when (logLevel) {
                LogLevels.ERRORS_AND_WARNS, LogLevels.ALL_LOGS -> printMessage(msg, "WARN")
                else -> return
            }
        }

        var error: (String) -> Unit = fun(msg: String) {
            when (logLevel) {
                LogLevels.ERRORS_AND_WARNS, LogLevels.ALL_LOGS, LogLevels.ERRORS_ONLY -> printMessage(
                    msg,
                    "ERROR"
                )

                else -> return
            }
        }

        private fun printMessage(msg: String, level: String) {
            val message = "[MealzSharedAnalytics][$level] $msg \n"
            print(message)
            if (keepLog) stackTrace += message
        }
    }
}
