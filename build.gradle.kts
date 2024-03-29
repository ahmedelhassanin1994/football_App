
// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    id("com.android.application") version "8.0.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id ("com.google.dagger.hilt.android") version  "2.50" apply false
    id ("com.android.library") version "8.0.1" apply false

}
buildscript {
    repositories {
        // other repositories...
        mavenCentral()
    }
    dependencies {
        // other plugins...
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.50")
//        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.1")
    }

}