apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.coreUi))
    "implementation"(project(Modules.beerGuideDomain))
    "implementation"(project(Modules.testing))
    "implementation"(Compose.coil)
}
