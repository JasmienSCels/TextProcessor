package com.example.data.datasource.local.database.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.data.dataSource.local.database.AppDatabase
import com.example.data.dataSource.local.database.dao.WordDAO
import com.example.data.model.WordLM
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException

@RunWith(MockitoJUnitRunner::class)
class WordDAOTests {

    private lateinit var database: AppDatabase
    private lateinit var dao: WordDAO

    @Before
    fun initDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java
        ).build()
        dao = database.wordDAO()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }

    @Test
    fun shouldInsertWord() {
        val word = WordLM(
            id = 1,
            title = "Test",
            freq = 29,
            isPrime = true,
            createdAt = System.currentTimeMillis()
        )

        dao.insert(word)

        val result = dao.loadAllWords()
        MatcherAssert.assertThat(result[0], CoreMatchers.equalTo(word))

    }


    @Test
    fun shouldDeleteWord() {
        val word = WordLM(
            id = 1,
            title = "Test",
            freq = 29,
            isPrime = true,
            createdAt = System.currentTimeMillis()
        )
        dao.insert(word)
        dao.delete(word)

        val result = dao.loadAllWords()

        Assert.assertTrue(result.isEmpty())
    }

    @Test
    fun shouldReturnWord() {
        val word = WordLM(
            id = 1,
            title = "Test",
            freq = 29,
            isPrime = true,
            createdAt = System.currentTimeMillis()
        )
        dao.insert(word)

        val result = dao.loadWord(1)

        MatcherAssert.assertThat(result, CoreMatchers.equalTo(word))
    }

    @Test
    fun shouldReturnWords() {
        val words = listOf(
            WordLM(id = 1, title = "Test", freq = 29, isPrime = true, createdAt = System.currentTimeMillis()),
            WordLM(id = 2, title = "Test2", freq = 4, isPrime = false, createdAt = System.currentTimeMillis())
        )
        dao.insertAll(words)

        val result = dao.loadAllWords()

        MatcherAssert.assertThat(result, CoreMatchers.equalTo(words))
    }


}