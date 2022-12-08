plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.sample.project.beerexplorer"
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        applicationId = "com.sample.project.beerexplorer"
        minSdk = ConfigData.minSdkVersion
        targetSdk = ConfigData.targetSdkVersion
        versionCode = ConfigData.versionCode
        versionName = ConfigData.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        kapt {
            arguments {
                arg("room.schemaLocation", "$projectDir/roomSchemas")
            }
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Compose.compose
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(project(Modules.core))
    implementation(project(Modules.coreUi))

    // feature - Onboarding
    implementation(project(Modules.onboardingPresentation))
    implementation(project(Modules.onboardingDomain))
    implementation(project(Modules.onboardingData))

    // feature - Beer Guide
    implementation(project(Modules.beerGuidePresentation))
    implementation(project(Modules.beerGuideDomain))
    implementation(project(Modules.beerGuideData))

    implementation(project(Modules.testing))

    // Compose
    implementation(Compose.composeNavigation)
    implementation(Compose.composeUi)
    implementation(Compose.composeMaterial)
    implementation(Compose.composeUiTooling)
    debugImplementation(Compose.composeUiToolingPreview)
    implementation(Compose.composeActivity)
    implementation(Compose.coil)

    // SplashScreen
    implementation(CommonDependencies.splashScreen)

    // UI
    implementation(CommonDependencies.material)
    implementation(Accompanist.navigationAnimation)

    // Room
    implementation(Room.roomRuntime)
    implementation(Room.roomKtx)
    kapt(Room.roomCompiler)

    // Networking
    implementation(Retrofit.retrofit2)
    implementation(Retrofit.gsonConverter)
    implementation(Retrofit.httpLoggingInterceptor)

    // DI, Hilt
    implementation(DaggerHilt.hiltAndroid)
    kapt(DaggerHilt.hiltCompiler)

    // Tests
    testImplementation(Testing.junit4)
    testImplementation(Testing.junitAndroidExt)
    testImplementation(Testing.truth)
    testImplementation(Testing.coroutines)
    testImplementation(Testing.turbine)
    testImplementation(Testing.composeUiTest)
    testImplementation(Testing.mockk)
    testImplementation(Testing.mockWebServer)
    testImplementation(Testing.kotlinTest)

    androidTestImplementation(Testing.junit4)
    androidTestImplementation(Testing.junitAndroidExt)
    androidTestImplementation(Testing.truth)
    androidTestImplementation(Testing.coroutines)
    androidTestImplementation(Testing.turbine)
    androidTestImplementation(Testing.composeUiTest)
    androidTestImplementation(Testing.mockk)
    androidTestImplementation(Testing.mockWebServer)
    androidTestImplementation(Testing.hiltTesting)
}
