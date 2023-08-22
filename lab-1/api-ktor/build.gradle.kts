plugins {
    kotlin("jvm") version libs.versions.kotlin.get()
    kotlin("plugin.serialization") version libs.versions.kotlin.get()
    id("io.ktor.plugin") version libs.versions.ktor.get()
}

group = "xyz.ajraspi.cc.webservice"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.bundles.ktor.server)
    implementation(libs.ktor.server.config.yaml)
    implementation(libs.bundles.ktor.call.logging)
    implementation(libs.bundles.ktor.content.negotiation)
    implementation(libs.logback)
}

application {
    mainClass = "io.ktor.server.netty.EngineMain"
}