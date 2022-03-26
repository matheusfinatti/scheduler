/**
 * Describes all dependencies versions.
 */
object Versions {
    // App version
    const val versionCode = 1
    const val versionName = "1.0.0"

    // Android versions
    const val compileSdk = 31
    const val targetSdk = 31
    const val minSdk = 21
    const val buildTools = "31.0.0"

    // Gradle plugins
    val plugins = Gradle
    object Gradle {
        const val android = "7.1.2"
    }

    // Library dependencies
    const val kotlin = "1.6.10"
    const val coroutines = "1.3.9"
    const val core = "1.7.0"
    const val compose = "1.1.1"
    const val lifecycle = "2.3.1"
    const val activity = "1.4.0"
    const val koin = "3.2.0-beta-1"
    const val timber = "4.7.1"
    const val retrofit = "2.9.0"
    const val moshi = "1.13.0"
    const val moshiConverter = "2.9.0"
    const val httpLogging = "4.9.3"
    const val desugar = "1.1.5"
    const val navigation = "2.4.1"
    const val coil = "2.0.0-rc02"

    // Testing dependencies
    const val jUnit = "4.13.2"
    const val mockk = "1.12.3"
    val androidTest = AndroidTest
    object AndroidTest {
        const val jUnit = "1.1.3"
        const val espresso = "3.4.0"
    }
    const val turbine = "0.7.0"
    const val mockWebServer = "4.9.3"

    // Quality dependencies
    const val detekt = "1.20.0-RC1"
    const val ktlint = "0.45.1"
}

object Dependencies {
    val android = AndroidDependencies
    val test = TestDependencies
    val androidTest = AndroidTestDependencies
    val quality = QualityDependencies
}

object AndroidDependencies {
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    const val core = "androidx.core:core-ktx:${Versions.core}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val httpLogging = "com.squareup.okhttp3:logging-interceptor:${Versions.httpLogging}"
    const val coil = "io.coil-kt:coil-compose:${Versions.coil}"
    val moshi = Moshi
    object Moshi {
        const val base = "com.squareup.moshi:moshi:${Versions.moshi}"
        const val kotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
        const val converter = "com.squareup.retrofit2:converter-moshi:${Versions.moshiConverter}"
    }
    const val desugar = "com.android.tools:desugar_jdk_libs:${Versions.desugar}"
    val compose = Compose
    object Compose {
        const val ui = "androidx.compose.ui:ui:${Versions.compose}"
        const val material = "androidx.compose.material:material:${Versions.compose}"
        const val preview = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
        const val tooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    }
    val lifecycle = Lifecycle
    object Lifecycle {
        const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    }
    val activity = Activity
    object Activity {
        const val compose = "androidx.activity:activity-compose:${Versions.activity}"
    }
    val koin = Koin
    object Koin {
        const val core = "io.insert-koin:koin-core:${Versions.koin}"
        const val android = "io.insert-koin:koin-android:${Versions.koin}"
        const val compose = "io.insert-koin:koin-androidx-compose:${Versions.koin}"
    }
    val navigation = Navigation
    object Navigation {
        const val ui = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
        const val feature = "androidx.navigation:navigation-dynamic-features-fragment:${Versions.navigation}"
        const val compose = "androidx.navigation:navigation-compose:${Versions.navigation}"
    }
}

object TestDependencies {
    const val jUnit = "junit:junit:${Versions.jUnit}"
    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val mockkAndroid = "io.mockk:mockk-android:${Versions.mockk}"
    const val koin = "io.insert-koin:koin-test:${Versions.koin}"
    const val koinjunit = "io.insert-koin:koin-test-junit4:${Versions.koin}"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    const val turbine = "app.cash.turbine:turbine:${Versions.turbine}"
    const val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.mockWebServer}"
}

object AndroidTestDependencies {
    const val jUnit = "androidx.test.ext:junit-ktx:${Versions.androidTest.jUnit}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.androidTest.espresso}"
    const val compose = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
}

object QualityDependencies {
    const val ktlint = "com.pinterest:ktlint:${Versions.ktlint}"
    const val detekt = "io.gitlab.arturbosch.detekt:detekt-cli:${Versions.detekt}"
}
