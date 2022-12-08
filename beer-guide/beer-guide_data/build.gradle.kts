apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.beerGuideDomain))
    "implementation"(project(Modules.testing))
    "implementation"(Retrofit.retrofit2)
    "implementation"(Retrofit.httpLoggingInterceptor)
    "implementation"(Retrofit.gsonConverter)
    "kapt"(Room.roomCompiler)
    "implementation"(Room.roomKtx)
    "implementation"(Room.roomRuntime)
}
