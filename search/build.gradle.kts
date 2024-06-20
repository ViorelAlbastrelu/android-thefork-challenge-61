plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    compileSdk = 33

    defaultConfig {
        minSdk = 23
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.8.10")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.recyclerview:recyclerview:1.2.1")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    implementation(project(":api"))

    testImplementation("junit:junit:4.13.2")
}
