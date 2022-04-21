package com.server.repository

import com.server.model.Word
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.coroutine.insertOne
import org.litote.kmongo.eq
import org.litote.kmongo.reactivestreams.KMongo

class DatabaseFactory {
    private val client = KMongo.createClient().coroutine
    private val database = client.getDatabase("words")
    val wordsCollection = database.getCollection<Word>()

    suspend fun addWord(word: Word): Word {
        wordsCollection.insertOne(word)
        return word
    }

    suspend fun getAllWords(): List<Word> = wordsCollection.find().toList()

    suspend fun getWordById(wordId: String): Word? {
        return wordsCollection.findOne(Word::id eq wordId)
    }

    suspend fun deleteWordById(wordId : String): Boolean {
        return wordsCollection.deleteOne(Word::id eq wordId).wasAcknowledged()
    }

    suspend fun getWordByRu(wordRu: String): Word? {
        return wordsCollection.findOne(Word::ruWord eq wordRu)
    }

    suspend fun getWordByCh(wordCh: String): Word? {
        return wordsCollection.findOne(Word::chWord eq wordCh)
    }

    suspend fun getWordByEn(wordEn: String): Word? {
        return wordsCollection.findOne(Word::enWord eq wordEn)
    }
    
}