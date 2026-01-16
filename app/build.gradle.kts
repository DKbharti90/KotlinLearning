plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")         // only for Room
    id("com.apollographql.apollo")
    id("org.jetbrains.kotlin.plugin.compose")
}

apollo {
    service("service") {
        packageName.set("com.example.kotlinlearning")
        introspection {
            endpointUrl.set("https://countries.trevorblades.com/graphql")
            schemaFile.set(file("src/main/graphql/schema.graphqls"))
            generateKotlinModels.set(true)
            // headers.put("Authorization", "Bearer YOUR_TOKEN_HERE")
        }
    }
}

android {
    namespace = "com.example.kotlinlearning"
    compileSdk = 35
    defaultConfig {
        applicationId = "com.example.kotlinlearning"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        javaCompileOptions {
            annotationProcessorOptions {
                argument("room.schemaLocation", "$projectDir/schemas")
            }
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions { jvmTarget = "17" }
    kotlin { jvmToolchain(17) }
    buildFeatures {
        dataBinding = true
        compose =true
    }
    buildToolsVersion = "35.0.0"


}



dependencies {

    val retrofitVersion = "2.11.0"
    val okhttp_version = "5.0.0-alpha.14"
    val coroutines_version = "1.9.0"
    val room_version = "2.8.4"
    val hilt_version = "2.51.1"
    val paging_version = "3.3.6"
    val apollo_version = "4.1.0"

    // AndroidX core libraries
    implementation("androidx.core:core-ktx:1.16.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.13.0")
    implementation("androidx.constraintlayout:constraintlayout:2.2.1")

    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.10.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.10.0")

    // Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.9.6")
    implementation("androidx.navigation:navigation-ui-ktx:2.9.6")

    // Room (KSP)
    implementation("androidx.room:room-runtime:$room_version")
    ksp("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-ktx:$room_version")
    implementation("androidx.room:room-paging:$room_version")

    // Paging
    implementation("androidx.paging:paging-runtime-ktx:$paging_version")
    implementation("androidx.paging:paging-rxjava2-ktx:$paging_version")

    // Hilt
    implementation("com.google.dagger:hilt-android:$hilt_version")
    ksp("com.google.dagger:hilt-android-compiler:$hilt_version")



    // Retrofit + OkHttp
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    implementation("com.squareup.okhttp3:okhttp:$okhttp_version")
    implementation("com.squareup.okhttp3:logging-interceptor:$okhttp_version")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version")

    // Apollo GraphQL
    implementation("com.apollographql.apollo:apollo-runtime:$apollo_version")

    // Kotlin stdlib
    implementation(kotlin("stdlib"))

    // Metadata
    implementation("org.jetbrains.kotlinx:kotlinx-metadata-jvm:0.9.0")

    // Tests
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")

    implementation(platform("androidx.compose:compose-bom:2024.12.01"))
    implementation("androidx.compose.runtime:runtime")


}