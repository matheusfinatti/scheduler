object GradlePlugins {
    val android = Android
    object Android {
        const val application = "com.android.application"
        const val library = "com.android.library"
    }
    val kotlin = Kotlin
    object Kotlin {
        const val android = "org.jetbrains.kotlin.android"
    }
}
