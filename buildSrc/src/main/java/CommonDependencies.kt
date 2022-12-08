object CommonDependencies {
    private const val splashScreenVersion = "1.0.0"
    private const val materialVersion = "1.6.1"

    val androidxNavSafeArgs by lazy { "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navVersion}" }
    val material by lazy { "com.google.android.material:material:$materialVersion" }
    val splashScreen by lazy { "androidx.core:core-splashscreen:$splashScreenVersion" }
}
