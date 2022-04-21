package com.server

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.server.plugins.*
import com.server.repository.DatabaseFactory

fun main() {
    embeddedServer(Netty, port = 8080, host = "127.0.0.1") {
        configureRouting()
        configureSerialization()
        configureTemplating()
        configureHTTP()
    }.start(wait = true)

}
