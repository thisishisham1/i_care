plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.icare"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.icare"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Core Kotlin extensions
    implementation(libs.androidx.core.ktx)
    // AndroidX lifecycle extensions
    implementation(libs.androidx.lifecycle.runtime.ktx)
    // Compose activity integration
    implementation(libs.androidx.activity.compose)
    // Jetpack Compose dependencies
    implementation(platform(libs.androidx.compose.bom.v20240300))
    //noinspection UseTomlInstead
    implementation("androidx.compose.ui:ui")
    //noinspection UseTomlInstead
    implementation("androidx.compose.ui:ui-graphics")
    //noinspection UseTomlInstead
    implementation("androidx.compose.ui:ui-tooling-preview")
    //noinspection UseTomlInstead
    implementation("androidx.compose.material3:material3")
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    // Unit testing dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.compose.bom.v20240300))
    //noinspection UseTomlInstead
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    //noinspection UseTomlInstead
    debugImplementation("androidx.compose.ui:ui-tooling")
    //noinspection UseTomlInstead
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    // Navigation Component for Jetpack Compose
    implementation(libs.androidx.navigation.compose)
    //View model
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    // Retrofit for HTTP requests
    implementation(libs.retrofit.v290)
    implementation(libs.converter.gson.v290)
    // Coil for image loading in Compose
    implementation(libs.coil.compose)
    implementation(libs.coil)
    // Room for storing data
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)
    ksp(libs.androidx.room.compiler)
    //hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    //faker
    implementation(libs.javafaker)
    //stream sdk
    implementation(libs.stream.chat.android.compose.v608)
    implementation(libs.stream.chat.android.offline.v608)
    implementation(libs.androidx.material.icons.extended.v165)
}