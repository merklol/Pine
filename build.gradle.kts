plugins {
    id("com.android.application") version "8.0.0" apply false
    id("com.android.library") version "8.0.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("io.gitlab.arturbosch.detekt").version("1.19.0")
}

tasks.register<io.gitlab.arturbosch.detekt.Detekt>("detektAll") {
    description = "Runs a custom detekt build for all modules"
    setSource(projectDir)
    config.setFrom(project.file("config/detekt/detekt.yml"))
    parallel = true
    reports {
        html {
            required.set(true)
            outputLocation.set(file("$rootDir/reports/detekt/detekt.html"))
        }
    }
    include("**/*.kt")
    exclude("**/resources/**", "**/build/**")
}

tasks.withType<io.gitlab.arturbosch.detekt.DetektCreateBaselineTask>().configureEach {
    jvmTarget = "11"
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}