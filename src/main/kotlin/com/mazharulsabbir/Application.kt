package com.mazharulsabbir

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.mazharulsabbir.plugins.*
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.serialization.*
import com.mazharulsabbir.routes.*
import io.ktor.gson.*
import io.ktor.http.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureRouting()
        configureSecurity()
        configureHTTP()
        install(ContentNegotiation) {
            gson {
                setPrettyPrinting()
                disableHtmlEscaping()
            }
        }
        registerUserRoutes()
    }.start(wait = true)
}