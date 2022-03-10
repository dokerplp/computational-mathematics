package dokerplp.util

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class CustomLogger {
    private val logger: Logger = LoggerFactory.getLogger(CustomLogger::class.java)

    fun log (message: String) {
        println("$message\n")
    }
    fun log (message: String, exception: RuntimeException) {
        logger.error(message, exception)
    }
}