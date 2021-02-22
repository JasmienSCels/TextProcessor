package com.example.data.dataSource.local.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.data.model.WordLM

@Dao
abstract class WordDAO : BaseDAO<WordLM> {

    @Query("SELECT * FROM WordLM")
    abstract fun loadAllWords(): List<WordLM>

    @Query("SELECT * FROM WordLM WHERE id = :id")
    abstract fun loadWord(id: Long): WordLM

    @Query("DELETE FROM WordLM")
    abstract fun deleteAllWords()

}