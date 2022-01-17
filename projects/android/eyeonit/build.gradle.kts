plugins {
    id("com.android.application")
    id("kotlin-android")
    id("com.microsoft.intune.mam")
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "io.eyeonit.eyeonit"
        minSdk=21
        targetSdk=31
        versionCode = 1
        versionName = "1.0"

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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.0.4"
    }
}

dependencies {

    implementation(project(":secure"))
    implementation(files("../deps/ms-intune-app-sdk-android-8.0.0/Microsoft.Intune.MAM.SDK.aar"))

    //Kotlin Co-routines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")

    val compose_version = "1.0.4"

    implementation("androidx.core:core-ktx:1.6.0")
    implementation("androidx.compose.ui:ui:$compose_version")
    implementation("androidx.compose.material:material:$compose_version")
    implementation("androidx.compose.material:material-icons-extended:$compose_version")
    implementation("androidx.activity:activity-compose:1.3.1")
    implementation("androidx.compose.ui:ui-tooling:$compose_version")
    implementation("androidx.navigation:navigation-compose:2.4.0-alpha10")

    // UI Tests
    testImplementation("junit:junit:4.+")
    androidTestImplementation("com.android.support.test:runner:1.0.2")
    androidTestImplementation("com.android.support.test.espresso:espresso-core:3.0.2")

    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.0.1")

    implementation("androidx.datastore:datastore:1.0.0")

    implementation("io.ktor:ktor-client-core:1.6.4")
    implementation("io.ktor:ktor-client-cio:1.6.4")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")
}

configure<com.microsoft.intune.mam.MamifyConfiguration> {
    excludeProjects = setOf(":secure")
}
