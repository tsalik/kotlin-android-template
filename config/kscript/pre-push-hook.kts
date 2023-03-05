#!/usr/bin/env kscript
import java.io.File

println("Running pre-push hook")
"./gradlew detekt".runWithRedirect()
"./gradlew test".runWithRedirect()

fun error(message: String, throwable: Throwable? = null, statusCode: Int = 1) {
    System.err.println("❌\t$message")
    System.exit(status)
}

fun String.runWithRedirect(directory: File? = null) {
    val status = ProcessBuilder("/bin/sh", "-c", this)
        .redirectErrorStream(true)
        .inheritIO()
        .directory(directory)
        .start()
        .waitFor()
    if (status == ProcessResult.ERROR) {
        error("Failed running: $this")
    }
}

object ProcessResult {
    const val ERROR = 1
}
