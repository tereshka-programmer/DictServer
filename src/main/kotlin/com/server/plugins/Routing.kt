package com.server.plugins

import com.server.repository.DatabaseFactory
import com.server.routes.wordRouting
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*

fun Application.configureRouting() {
    val db = DatabaseFactory()

    routing {
        wordRouting(db)
    }
}
