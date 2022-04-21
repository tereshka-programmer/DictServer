package com.server.model

import de.undercouch.bson4jackson.types.ObjectId
import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

@Serializable
data class Word(
    @BsonId
    val id: String = org.bson.types.ObjectId().toString(),
    val enWord: String,
    val ruWord: String,
    val chWord: String,
    val chTranscript: String)
