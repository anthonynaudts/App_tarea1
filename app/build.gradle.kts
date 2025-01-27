plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.app_tarea1"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.app_tarea1"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    // AndroidX and Jetpack components
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.2")

    // Retrofit (for API communication)
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // OkHttp (for HTTP requests)
    implementation("com.squareup.okhttp3:okhttp:4.9.0")

    // Room for offline data storage
    implementation("androidx.room:room-runtime:2.3.0")
    annotationProcessor("androidx.room:room-compiler:2.3.0") // For Java

    // Glide (for image loading)
    implementation("com.github.bumptech.glide:glide:4.11.0")

    // ViewModel and LiveData
    implementation("androidx.lifecycle:lifecycle-viewmodel:2.3.1")
    implementation("androidx.lifecycle:lifecycle-livedata:2.3.1")

    implementation("androidx.activity:activity-ktx:1.3.1")   // Para usar activity-ktx
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1")  // Para ViewModel
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.3.1")  // Para LiveData si lo necesitas
}