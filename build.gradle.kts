// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    kotlin("kapt") version "1.8.10"
    id("com.google.devtools.ksp") version "1.8.0-1.0.8" apply false
}
//1.8.0-1.0.8
buildscript{

    val hiltVersion: String by project

    dependencies{
        classpath("com.android.tools.build:gradle:3.2.0")
        classpath("com.google.dagger:hilt-android-gradle-plugin:$hiltVersion")
    }

}