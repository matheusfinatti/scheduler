import org.gradle.internal.impldep.com.google.api.services.storage.model.Bucket

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
    const val core = "1.7.0"
    const val compose = "1.1.1"
    const val lifecycle = "2.3.1"
    const val activity = "1.4.0"

    // Testing dependencies
    const val jUnit = "4.13.2"
    const val mockk = "1.12.3"
    val androidTest = AndroidTest
    object AndroidTest {
        const val jUnit = "1.1.3"
        const val espresso = "3.4.0"
    }
}

object Dependencies {
    val android = AndroidDependencies
}

object AndroidDependencies {
    const val core = "androidx.core:core-ktx:${Versions.core}"
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
}

object TestDependencies {
    const val jUnit = "junit:junit:${Versions.jUnit}"
    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val mockkAndroid = "io.mockk:mockk-android:${Versions.mockk}"
}

object AndroidTestDependencies {
    const val jUnit = "androidx.test.ext:junit-ktx:${Versions.androidTest.jUnit}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.androidTest.espresso}"
    const val compose = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
}
