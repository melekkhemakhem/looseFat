// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    dependencies {
        classpath("com.google.gms:google-services:4.4.1")
    }
}
plugins {

    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    id("checkstyle")
}

checkstyle {
    toolVersion = "8.45"
    configFile = file("config/checkstyle/checkstyle.xml")
}

tasks.register("checkstyle", Checkstyle::class) {
    source("src")
    include("**/*.java")
    classpath = files()
    reports {
        xml.required.set(true)
        html.required.set(true)
    }
}
