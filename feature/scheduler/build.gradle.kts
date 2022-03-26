plugins {
    id(GradlePlugins.android.library)
    id(GradlePlugins.kotlin.android)
}

apply(from = "$rootDir/config/quality.gradle.kts")

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

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
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

    implementation(Dependencies.android.core)
    implementation(Dependencies.android.compose.preview)
    implementation(Dependencies.android.compose.material)
    implementation(Dependencies.android.compose.ui)
    debugImplementation(Dependencies.android.compose.tooling)

    coreLibraryDesugaring(Dependencies.android.desugar)

    testImplementation(Dependencies.test.jUnit)
    testImplementation(Dependencies.test.mockk)
    testImplementation(Dependencies.test.mockkAndroid)
    testImplementation(Dependencies.test.coroutines)
    testImplementation(Dependencies.test.turbine)
}
