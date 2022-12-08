apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.coreUi))
    "implementation"(project(Modules.onboardingDomain))
    "implementation"(project(Modules.testing))
    "implementation"(Accompanist.pager)
    "implementation"(Accompanist.pagerIndicators)
}
