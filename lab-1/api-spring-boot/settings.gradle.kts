rootProject.name = "Cloud Computing Lab 1 (Spring Boot)"

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
    versionCatalogs {
        create("libs") {
            version("kotlin", "1.9.0")
            version("kotlinx.serialization", "1.6.0-RC")
            version("springframework", "3.1.2")
            version("spring", "1.1.2")

            library("kotlin-reflect", "org.jetbrains.kotlin", "kotlin-reflect").versionRef("kotlin")
            library(
                "kotlinx-serialization",
                "org.jetbrains.kotlinx",
                "kotlinx-serialization-json",
            ).versionRef("kotlinx.serialization")
            library(
                "spring-starter",
                "org.springframework.boot",
                "spring-boot-starter-web",
            ).versionRef("springframework")
        }
    }
}