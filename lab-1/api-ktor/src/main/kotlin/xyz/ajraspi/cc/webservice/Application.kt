package xyz.ajraspi.cc.webservice

import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.callid.*
import io.ktor.server.plugins.callloging.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNamingStrategy
import org.slf4j.event.Level
import xyz.ajraspi.cc.webservice.course.courseRoutes

fun main(args: Array<String>) = EngineMain.main(args)

@ExperimentalSerializationApi
fun Application.module() {
    install(CallLogging) {
        level = Level.INFO
    }
    install(CallId) {
        header(HttpHeaders.XRequestId)
    }
    install(ContentNegotiation) {
        json(
            Json {
                prettyPrint = true
                isLenient = true
                namingStrategy = JsonNamingStrategy.SnakeCase
                encodeDefaults = true
            }
        )
    }

    courseRoutes()
}
