package com.mazharulsabbir.routes

import com.mazharulsabbir.websocket.Connection
import io.ktor.application.*
import io.ktor.http.cio.websocket.*
import io.ktor.routing.*
import io.ktor.utils.io.*
import io.ktor.websocket.*
import java.lang.Exception
import java.util.*
import kotlin.collections.LinkedHashSet

fun Route.chatRouting() {
    val connections = Collections.synchronizedSet<Connection?>(LinkedHashSet())

    webSocket("/chat") {
        println("Adding user!")
        send("You are connected!")

        val thisConnection = Connection(this)
        connections += thisConnection

        try {
            for (frame in incoming) {
                frame as? Frame.Text ?: continue
                val receivedText = frame.readText()
//                send("You said: $receivedText")

                val textWithUsername = "[${thisConnection.name}]: $receivedText"
                connections.forEach {
                    it.session.send(textWithUsername)
                }
            }
        } catch (e: Exception) {
            println(e.printStackTrace())
        } finally {
            println("Removing $thisConnection!")
            connections -= thisConnection
        }
    }
}

fun Application.registerChatRoutes() {
    routing {
        chatRouting()
    }
}
