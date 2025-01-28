import io.gitlab.arturbosch.detekt.Detekt

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.detekt)
}

detekt {
    config.from(files(rootProject.files("config/detekt/detekt.yml")))
    buildUponDefaultConfig = true
    autoCorrect = true

    source.from(files(projectDir))
    tasks.withType<Detekt>().configureEach {
        reports {
            xml.required.set(false)
            txt.required.set(false)
            sarif.required.set(false)
            html {
                required.set(true)
                outputLocation.set(file("build/reports/detekt.html"))
            }
        }
    }
}

dependencies {
    detektPlugins(libs.detekt.formatting)
    detektPlugins(libs.detekt.rules)
}
