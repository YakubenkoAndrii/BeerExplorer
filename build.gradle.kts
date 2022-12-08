// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version Versions.androidLibrary apply false
    id("com.android.library") version Versions.androidLibrary apply false
    id("org.jetbrains.kotlin.android") version Versions.kotlin apply false
    id("com.google.dagger.hilt.android") version DaggerHilt.hiltVersion apply false
    id("org.jlleitschuh.gradle.ktlint") version Versions.ktlint
}

buildscript {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
        maven { url = uri("https://oss.jfrog.org/libs-snapshot") }
    }
    dependencies {
        classpath(CommonDependencies.androidxNavSafeArgs)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}")
    }
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    ktlint {
        android.set(true)
        ignoreFailures.set(false)
        enableExperimentalRules.set(true)
        disabledRules.set(setOf("experimental:package-name", "import-ordering", "no-wildcard-imports"))
        reporters {
            reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.PLAIN)
            reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.CHECKSTYLE)
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
