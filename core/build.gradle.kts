plugins {
    id(GradlePlugins.android.library)
    id(GradlePlugins.kotlin.android)
}

apply(from = "$rootDir/config/quality.gradle.kts")

android {
    compileSdk = Versions.compileSdk

    defaultConfig {
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildTypes {
            release {
                isMinifyEnabled = true
                proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            }

            debug {
                isMinifyEnabled = false
            }
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }

        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}

dependencies {
    implementation(Dependencies.android.timber)
    implementation(Dependencies.android.httpLogging)
    implementation(Dependencies.test.mockWebServer) // Specially for this case

    api(Dependencies.android.core)
    api(Dependencies.android.moshi.base)
    api(Dependencies.android.moshi.kotlin)
    api(Dependencies.android.moshi.converter)
    api(Dependencies.android.koin.core)
    api(Dependencies.android.koin.android)
    api(Dependencies.android.koin.compose)
    api(Dependencies.android.retrofit)

    testImplementation(Dependencies.test.jUnit)
}
