plugins {
    id("com.android.library")
    id("kotlin-android")
    id("com.google.devtools.ksp") version "1.5.31-1.0.0"
    kotlin("kapt")
}

android {
    compileSdk = 31

    defaultConfig {
        minSdk = 21
        targetSdk =31

        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
        //consumerProguardFiles = "consumer-rules.pro"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        val options = this as org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
        options.jvmTarget = "1.8"
    }
}

dependencies {

    /**
     * Android UI Libraries
     */
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.1")

    /**
     * MSAL
     * https://mvnrepository.com/artifact/com.microsoft.identity.client/msal
     */
    implementation("com.microsoft.identity.client:msal:2.2.1")

    /**
     * Start Work Manager     */
    val workVersion = "2.6.0"

    // (Java only)
    implementation("androidx.work:work-runtime:$workVersion")
    // Kotlin + coroutines
    implementation("androidx.work:work-runtime-ktx:$workVersion")
    // optional - RxJava2 support
    implementation("androidx.work:work-rxjava2:$workVersion")
    // optional - GCMNetworkManager support
    implementation("androidx.work:work-gcm:$workVersion")
    // optional - Test helpers
    androidTestImplementation("androidx.work:work-testing:$workVersion")
    // optional - Multiprocess support
    implementation("androidx.work:work-multiprocess:$workVersion")
    /**
     * End Work Manager
     */

    //Data Store
    implementation("androidx.datastore:datastore:1.0.0")

    /**
     * Start Room Dependencies
     */
    //Kotlin Symbolic processing
    implementation("com.google.devtools.ksp:symbol-processing-api:1.5.31-1.0.0")

    val roomVersion = "2.3.0"

    implementation("androidx.room:room-runtime:$roomVersion")
    annotationProcessor("androidx.room:room-compiler:$roomVersion")

    // To use Kotlin annotation processing tool (kapt)
    kapt("androidx.room:room-compiler:$roomVersion")
    // To use Kotlin Symbolic Processing (KSP)
    ksp("androidx.room:room-compiler:$roomVersion")

    // optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:$roomVersion")

    // optional - RxJava2 support for Room
    implementation("androidx.room:room-rxjava2:$roomVersion")

    // optional - RxJava3 support for Room
    implementation("androidx.room:room-rxjava3:$roomVersion")

    // optional - Guava support for Room, including Optional and ListenableFuture
    implementation("androidx.room:room-guava:$roomVersion")

    // optional - Test helpers
    testImplementation("androidx.room:room-testing:$roomVersion")

    // optional - Paging 3 Integration
    implementation("androidx.room:room-paging:2.4.0-alpha05")

    /**
     * End Room Dependencies
     */

    //Kotlin Co-routines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")

    //Kotlin reflection
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.5.31")


    testImplementation("junit:junit:4.+")
    androidTestImplementation("com.android.support.test:runner:1.0.2")
    androidTestImplementation("com.android.support.test.espresso:espresso-core:3.0.2")
}