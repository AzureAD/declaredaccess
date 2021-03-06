/*
 * This file was generated by the Gradle 'init' task.
 *
 * This is a general purpose Gradle build.
 * Learn more about Gradle by exploring our samples at https://docs.gradle.org/7.1/samples
 */
plugins {
    kotlin("jvm") version "1.6.21" apply false
    kotlin("plugin.serialization") version "1.6.21"
}

buildscript {
    repositories {
        // Gradle 4.1 and higher include support for Google's Maven repo using
        // the google() method. And you need to include this repo to download
        // Android Gradle plugin 3.0.0 or higher.
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.21")
        classpath("com.android.tools.build:gradle:7.2.1")
        classpath("org.javassist:javassist:3.27.0-GA")
        classpath(kotlin("gradle-plugin", version = "1.6.21"))
        classpath(files("deps/ms-intune-app-sdk-android-8.0.0/GradlePlugin/com.microsoft.intune.mam.build.jar"))
    }
}

allprojects {
    repositories{
        google()
        mavenCentral()
        maven(url ="https://pkgs.dev.azure.com/MicrosoftDeviceSDK/DuoSDK-Public/_packaging/Duo-SDK-Feed/maven/v1")
    }
}
