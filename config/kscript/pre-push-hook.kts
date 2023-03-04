#!/usr/bin/env kscript
import java.io.File

println("Running pre-push hook")
"./gradlew detekt".runWithRedirect()

fun error(message: String, throwable: Throwable? = null, statusCode: Int = 1) {
    System.err.println("❌\t${ConsoleColors.ANSI_RED}$message${ConsoleColors.ANSI_RESET}")
}

fun String.runWithRedirect(directory: File? = null) {
    val status = ProcessBuilder("/bin/sh", "-c", this)
        .redirectErrorStream(true)
        .inheritIO()
        .directory(null)
        .start()
        .waitFor()
    if (status == 1) {
        error("Failed running: $this")
    }
    System.exit(status)
}

object ConsoleColors {
    const val ANSI_RED = "\u001B[0m"
    const val ANSI_RESET = "\u001B[31m"
}
