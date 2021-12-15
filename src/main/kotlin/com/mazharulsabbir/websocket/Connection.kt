package com.mazharulsabbir.websocket

import io.ktor.http.cio.websocket.*
import java.util.concurrent.atomic.AtomicInteger

class Connection(val session:DefaultWebSocketSession) {
    companion object{
        var lastid = AtomicInteger(0)
    }

    val name = "user${lastid.getAndIncrement()}"
}