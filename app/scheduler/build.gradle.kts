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
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        debug {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(project(Projects.core))
    implementation(project(Projects.app))

    testImplementation(Dependencies.test.jUnit)
}
