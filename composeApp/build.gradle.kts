import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.ksp)
    alias(libs.plugins.google.gms.services)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose)
    alias(libs.plugins.androidx.room)
    alias(libs.plugins.kotlin.serialization.plugin)
    alias(libs.plugins.kotlinCocoapods)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true  // KEEP THIS
        }
    }

    cocoapods {
        summary = "Yummy"
        version = "1.0"
        homepage = "https://github.com/arsen-R/Yummy"
        ios.deploymentTarget = "16.0"

        podfile = project.file("../iosApp/Podfile")

        framework {
            baseName = "ComposeApp"
            isStatic = true // Try changing this to true
        }
        //noPodspec()
        pod("FirebaseCore") {
            version = "11.15.0"
        }
        pod("FirebaseAuth") {
            version = "11.15.0"
        }
//        pod("FirebaseFirestoreInternal") {
//            version = "11.15.0"
//        }
//        pod("FirebaseFirestore") {
//            version = "11.15.0"
//            extraOpts += listOf("-compiler-option", "-fmodules")
//            useInteropBindingFrom("FirebaseFirestoreInternal")
//        }
    }
    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.kotlin.stdlib)
                implementation(libs.napier)

                implementation(project.dependencies.platform(libs.koin.bom))
                implementation(libs.koin.core)
                implementation(libs.koin.compose)
                implementation(libs.koin.compose.viewmodel)

                implementation(compose.runtime)
                implementation(compose.ui)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.components.resources)
                implementation(compose.components.uiToolingPreview)

                implementation(libs.navigation.compose)

                implementation(libs.kotlinx.coroutines.core)

                implementation(libs.lifecycle.viewmodel)
                implementation(libs.lifecycle.viewmodel.runtime)

                implementation(libs.sqlite.bundled)
                implementation(libs.androidx.room.runtime)

                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.kotlinx.serialization.core)

                implementation(libs.napier)

                implementation(libs.ktor.client.content.negotiation)
                implementation(libs.ktor.serialization.kotlinx.json)
                implementation(libs.ktor.logging)
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.serialization)


                implementation(libs.androidx.room.runtime)

                implementation(libs.datastore.preferences)
                implementation(libs.datastore)

                //implementation (libs.androidx.paging)

                implementation(libs.coil.compose)
                implementation(libs.coil.network)

                implementation(libs.paging.common)
                implementation(libs.paging.compose.common)

//                implementation("dev.gitlive:firebase-firestore:1.10.0") // Use the latest version
//                implementation("dev.gitlive:firebase-core:1.10.0")
            }
        }

        commonTest {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }

        androidMain {
            dependencies {
                implementation(libs.koin.android)
                implementation(libs.koin.androidx.compose)

                implementation(libs.ktor.client.android)

                implementation(project.dependencies.platform(libs.firebase.bom))
                implementation(libs.firebase.auth)
                implementation(libs.firebase.firestore)

                implementation(libs.androidx.paging.runtime)
                implementation(libs.androidx.paging.compose)

                implementation(libs.accompanist.systemuicontroller)
                implementation(libs.accompanist.flowlayout)
            }
        }
        iosMain {
            dependencies {
                implementation(libs.ktor.client.ios)
                implementation(libs.paging.runtime.uikit)
            }
        }
    }
}

android {
    namespace = "com.example.recipeapp"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.recipeapp"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
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
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    // Accompanist
    // Play Services Auth
    implementation(libs.play.services.auth)
    // Exoplayer
//    implementation (libs.androidx.media3.exoplayer)
//    implementation (libs.androidx.media3.ui)
//    implementation (libs.androidx.media3.common)
    // Credentials
    implementation(libs.androidx.credentials)
    implementation(libs.androidx.credentials.play.services.auth)
    implementation(libs.googleid)

    debugImplementation(compose.uiTooling)

    add("kspAndroid", libs.androidx.room.compiler)
    add("kspIosX64", libs.androidx.room.compiler)
    add("kspIosArm64", libs.androidx.room.compiler)
    add("kspIosSimulatorArm64", libs.androidx.room.compiler)
}

room {
    schemaDirectory("$projectDir/schemas") // Or any other desired path
}
compose.resources {
    publicResClass = true
    packageOfResClass = "com.example.recipeapp.resources"
    generateResClass = always
}