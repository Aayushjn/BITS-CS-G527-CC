rootProject.name = "Cloud Computing Lab 1"

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
    versionCatalogs {
        create("libs") {
            version("kotlin", "1.9.0")
            version("kotlinx.serialization", "1.6.0-RC")
            version("ktor", "2.3.3")
            version("logback", "1.2.11")

            library("ktor-server-core", "io.ktor", "ktor-server-core-jvm").versionRef("ktor")
            library("ktor-server-netty", "io.ktor", "ktor-server-netty-jvm").versionRef("ktor")
            library("ktor-server-config-yaml", "io.ktor", "ktor-server-config-yaml").versionRef("ktor")
            library("ktor-server-call-logging", "io.ktor", "ktor-server-call-logging").versionRef("ktor")
            library("ktor-server-call-id", "io.ktor", "ktor-server-call-id").versionRef("ktor")
            library(
                "ktor-server-content-negotiation",
                "io.ktor",
                "ktor-server-content-negotiation"
            ).versionRef("ktor")
            library(
                "ktor-server-serialization-json",
                "io.ktor",
                "ktor-serialization-kotlinx-json"
            ).versionRef("ktor")

            library("logback", "ch.qos.logback", "logback-classic").versionRef("logback")

            bundle("ktor-server", listOf("ktor-server-core", "ktor-server-netty"))
            bundle("ktor-call-logging", listOf("ktor-server-call-logging", "ktor-server-call-id"))
            bundle(
                "ktor-content-negotiation",
                listOf("ktor-server-content-negotiation", "ktor-server-serialization-json")
            )
        }
    }
}