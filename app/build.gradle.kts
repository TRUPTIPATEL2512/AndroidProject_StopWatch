plugins {
    alias(libs.plugins.android.application)
}

android {
    // Define the application namespace
    namespace = "com.example.truptipatelassignment1"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.truptipatelassignment1"
        minSdk = 30
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    // Enable ViewBinding for better UI management
    viewBinding {
        enable = true
    }
}

dependencies {
    implementation(libs.play.services.wearable)
    // AndroidX Support Libraries
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    // Wear OS specific dependencies
    implementation("androidx.wear:wear:1.2.0")
    implementation("androidx.wear.tiles:tiles:1.1.0")
    implementation("androidx.wear.watchface:watchface:1.1.0")
    // Material Design components
    implementation("com.google.android.material:material:1.5.0")
}
