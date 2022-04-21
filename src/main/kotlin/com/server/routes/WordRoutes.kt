package com.server.routes

import com.server.model.Word
import com.server.repository.DatabaseFactory
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.wordRouting(
    DB:DatabaseFactory
){
    route("/word") {

        post {
            val requestBody = call.receive<Word>()
            val word = DB.addWord(requestBody)
            call.respond(word)
        }

        get {
            val words = DB.getAllWords()
            call.respond(words)
        }

        get("/{id?}"){
            val id = call.parameters["id"] ?: return@get call.respondText(
                "Missing id",
                status = HttpStatusCode.BadRequest
            )
            val word =
                DB.getWordById(id) ?: return@get call.respondText(
                    "No word with id $id",
                    status = HttpStatusCode.NotFound
                )
            call.respond(word)
        }


        delete("{id?}"){
            val id = call.parameters["id"]

            val isDeleted = DB.deleteWordById(id!!)
            if (isDeleted) {
                call.respond("Deleted successfully")
            } else {
                call.respond("Id not found . . .")
            }
        }

        get("/ru/{ru?}"){
            val ruWord = call.parameters["ru"] ?: return@get call.respond("Missing word")

            val word = DB.getWordByRu(ruWord) ?: "No such word"
            call.respond(word)
        }

        get("/ch/{ch?}"){
            val chWord = call.parameters["ch"] ?: return@get call.respond("Missing word")

            val word = DB.getWordByCh(chWord) ?: "No such word"
            call.respond(word)
        }

        get("/en/{en?}"){
            val enWord = call.parameters["en"] ?: return@get call.respond("Missing word")

            val word = DB.getWordByRu(enWord) ?: "No such word"
            call.respond(word)
        }
    }
}