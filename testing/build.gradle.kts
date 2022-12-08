apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.onboardingDomain))
    "implementation"(project(Modules.beerGuideDomain))
    "implementation"(Testing.kotlinTest)
    "testImplementation"(Testing.kotlinTest)
    "implementation"(Testing.coroutines)
    "implementation"(Testing.junit4)
}
