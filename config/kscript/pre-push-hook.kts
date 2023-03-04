#!/usr/bin/env kscript
import java.io.File

println("Running pre-push hook")
detekt()


fun detekt() {
    "./gradlew detekt".runWithRedirect()
}

fun String.runWithRedirect(directory: File? = null): Int {
    return ProcessBuilder("/bin/sh", "-c", this)
        .redirectErrorStream(true)
        .inheritIO()
        .directory(null)
        .start()
        .waitFor()
}
