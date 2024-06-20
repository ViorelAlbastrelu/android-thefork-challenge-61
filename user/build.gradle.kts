plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    compileSdk = 33

    defaultConfig {
        minSdk = 23
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.8.10")
    implementation("androidx.appcompat:appcompat:1.6.1")

    implementation(platform("androidx.compose:compose-bom:2023.01.00"))
    implementation("androidx.compose.foundation:foundation")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.activity:activity-compose:1.6.1")
}
