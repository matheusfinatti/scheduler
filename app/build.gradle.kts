plugins {
    id(GradlePlugins.android.application)
    id(GradlePlugins.kotlin.android)
}

apply(from = "$rootDir/config/quality.gradle.kts")

android {
    compileSdk = Versions.compileSdk

    defaultConfig {
        applicationId = "com.example.officescheduler"
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk
        versionCode = Versions.versionCode
        versionName = Versions.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }

        debug {
            isMinifyEnabled = false
            isDebuggable = true
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }

    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }

}

dependencies {
    implementation(project(Projects.core))
    implementation(project(Projects.features.scheduler))
    implementation(project(Projects.navigation))

    implementation(Dependencies.android.core)
    implementation(Dependencies.android.compose.ui)
    implementation(Dependencies.android.compose.preview)
    implementation(Dependencies.android.compose.material)
    implementation(Dependencies.android.lifecycle.runtime)
    implementation(Dependencies.android.activity.compose)

    api(Dependencies.android.navigation.ui)
    api(Dependencies.android.navigation.feature)
    api(Dependencies.android.navigation.compose)

    testImplementation(Dependencies.test.jUnit)

    androidTestImplementation(Dependencies.androidTest.jUnit)
    androidTestImplementation(Dependencies.androidTest.espresso)
    androidTestImplementation(Dependencies.androidTest.compose)

    debugImplementation(Dependencies.android.compose.tooling)
}
