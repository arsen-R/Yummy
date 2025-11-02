import org.gradle.api.internal.DocumentationRegistry.BASE_URL

plugins {
    alias(libs.plugins.android.application)
//    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    //alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.google.gms.services)
}

android {
    namespace = "com.example.recipeapp"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.recipeapp"
        minSdk = 23
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary= true
        }
        buildConfigField("String", "BASE_URL", "\"https://tasty.p.rapidapi.com\"")
        buildConfigField("String", "HEADER_API_KEY", "\"856bebe788mshff1e35c3cc55068p104c2djsn64f4262291f5\"")
        buildConfigField("String", "HEADER_API_HOST", "\"tasty.p.rapidapi.com\"")
    }

    buildTypes {
        buildFeatures {
            buildConfig = true
            compose = true
        }
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility =JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation (libs.androidx.core.ktx)
    implementation (libs.androidx.activity.compose)
    implementation (libs.androidx.compose.ui)
    implementation (libs.androidx.compose.ui.tooling.preview)
    implementation (libs.androidx.compose.material)
    testImplementation (libs.junit)
    androidTestImplementation (libs.androidx.junit)
    androidTestImplementation (libs.androidx.espresso.core)
    androidTestImplementation (libs.androidx.compose.ui.test.junit4)
    debugImplementation (libs.androidx.compose.ui.tooling)
    debugImplementation (libs.androidx.compose.ui.test.manifest)
    // Accompanist
    implementation (libs.accompanist.systemuicontroller)
    implementation (libs.accompanist.swiperefresh)
    implementation (libs.accompanist.flowlayout)
    // Room
    implementation (libs.androidx.room.runtime)
    ksp (libs.androidx.room.compiler)
    implementation (libs.androidx.room.ktx)
    // Coroutines
    implementation (libs.kotlinx.coroutines.core)
    implementation (libs.kotlinx.coroutines.android)
    // OkHttp
    implementation (libs.okhttp)
    implementation (libs.logging.interceptor)
    // Retrofit
    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    // Navigation
    implementation (libs.androidx.navigation.compose)
    // Lifecycle
    implementation (libs.androidx.lifecycle.viewmodel.savedstate)
    implementation (libs.androidx.lifecycle.viewmodel.compose)
    implementation (libs.androidx.lifecycle.runtime.compose)
    implementation (libs.androidx.lifecycle.runtime.ktx)
    // Hilt
    implementation (libs.hilt.android)
    ksp (libs.hilt.compiler)
    implementation (libs.androidx.hilt.navigation.compose)
    // Coil
    implementation (libs.coil.compose)
    // Paging 3
    implementation (libs.androidx.paging.runtime.ktx)
    implementation (libs.androidx.paging.compose)
    // Firebase
    implementation (platform(libs.firebase.bom))
    implementation (libs.firebase.auth)
    implementation (libs.firebase.firestore)
    // Play Services Auth
    implementation (libs.play.services.auth)
    // Datastore
    implementation (libs.androidx.datastore.preferences)
    // Exoplayer
    implementation (libs.androidx.media3.exoplayer)
    implementation (libs.androidx.media3.ui)
    implementation (libs.androidx.media3.common)
    // Credentials
    implementation (libs.androidx.credentials)
    implementation (libs.androidx.credentials.play.services.auth)
    implementation (libs.googleid)
}