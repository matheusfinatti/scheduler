plugins {
    id(GradlePlugins.android.dynamicFeature)
    id(GradlePlugins.kotlin.android)
}
android {
    compileSdk = Versions.compileSdk

    defaultConfig {
        minSdk = Versions.minSdk
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        // Necessary to use JDK 1.8 date time in lower API levels (<26).
        isCoreLibraryDesugaringEnabled = true

        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(project(Projects.core))
    implementation(project(Projects.app))

    coreLibraryDesugaring(Dependencies.android.desugar)

    testImplementation(Dependencies.test.jUnit)
    testImplementation(Dependencies.test.mockk)
    testImplementation(Dependencies.test.mockkAndroid)
    testImplementation(Dependencies.test.coroutines)
}
