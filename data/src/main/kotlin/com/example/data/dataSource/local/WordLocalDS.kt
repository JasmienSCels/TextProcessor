package com.example.data.dataSource.local

import android.content.Context
import android.content.SharedPreferences
import com.example.data.dataSource.local.database.AppDatabase
import com.example.data.dataSource.local.database.dao.WordDAO
import com.example.data.model.WordLM
import com.example.data.model.toDM
import com.example.data.model.toLM
import com.example.data.repository.WordRepositoryImpl
import com.example.domain.model.WordFrequencyDM
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WordLocalDS @Inject constructor(
    private val db: AppDatabase,
    private val context: Context) :
    WordRepositoryImpl.LocalDS<WordFrequencyDM> {

    override fun save(model: WordFrequencyDM) {
        val lm = model.toLM()
        db.wordDAO().insert(lm)
    }

    override fun fetch(title: String): WordFrequencyDM {
        TODO("Not yet implemented")
    }

    override fun fetchAll(): Set<WordFrequencyDM> {
       return db.wordDAO().loadAllWords().map { it.toDM() }.toSet()
    }

    override fun delete(model: WordFrequencyDM) {
        TODO("Not yet implemented")
    }

    override fun deleteAll() {
        TODO("Not yet implemented")
    }

}