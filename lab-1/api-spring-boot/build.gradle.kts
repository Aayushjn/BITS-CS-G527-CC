import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version libs.versions.springframework.get()
    id("io.spring.dependency-management") version libs.versions.spring.get()
    kotlin("jvm") version libs.versions.kotlin.get()
    kotlin("plugin.serialization") version libs.versions.kotlin.get()
    kotlin("plugin.spring") version libs.versions.kotlin.get()
}

group = "xyz.ajraspi.cc.webservice"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.spring.starter)
    implementation(libs.kotlin.reflect)
    implementation(libs.kotlinx.serialization)
}

java {
    sourceCompatibility = JavaVersion.VERSION_19
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs += "-Xjsr305=strict"
            jvmTarget = "19"
        }
    }
}
