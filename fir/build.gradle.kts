plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
    id ("dagger.hilt.android.plugin")
    id ("kotlin-parcelize")
}

android {
    compileSdk = 32

    defaultConfig {
        applicationId = "com.baz.fir"
        minSdk = 21
        targetSdk = 32
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

    buildFeatures {
        viewBinding=true
        dataBinding=true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

//kapt {
//    correctErrorTypes=true
//}

dependencies {

    implementation ("androidx.core:core-ktx:1.8.0")
    implementation ("androidx.activity:activity-ktx:1.4.0")
    implementation ("androidx.appcompat:appcompat:1.4.2")
    implementation ("com.google.android.material:material:1.6.1")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")


    // Lifecycle
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.4.1")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.4.1")

    //Dagger - Hilt
    implementation ("com.google.dagger:hilt-android:2.40.5")
    kapt ("com.google.dagger:hilt-android-compiler:2.40.5")
//    implementation ("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03")
//    kapt ("androidx.hilt:hilt-compiler:1.0.0")

    // coroutine
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.0")

    // Glide
    implementation ("com.github.bumptech.glide:glide:4.13.0")
    kapt ("com.github.bumptech.glide:compiler:4.13.0")

    //nav component
    implementation ("androidx.navigation:navigation-fragment-ktx:2.4.2")
    implementation ("androidx.navigation:navigation-ui-ktx:2.4.2")

    // Gson
    implementation ("com.google.code.gson:gson:2.9.0")

    // firebase
//    implementation platform("com.google.firebase:firebase-bom:30.1.0")
//    implementation ("com.google.firebase:firebase-analytics-ktx")

    // For local unit tests
    testImplementation ("junit:junit:4.13.2")

//    testImplementation("com.google.dagger:hilt-android-testing:2.42")
//    kaptTest ("com.google.dagger:hilt-compiler:2.42")

    androidTestImplementation ("androidx.test.ext:junit:1.1.3")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.4.0")
//    androidTestImplementation  ("com.google.dagger:hilt-android-testing:2.42")
//    kaptAndroidTest ("com.google.dagger:hilt-compiler:2.42")
}