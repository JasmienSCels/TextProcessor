package com.example.data.repository

import com.example.domain.repository.WordRepository
import javax.inject.Inject

class WordRepositoryImpl<T> @Inject constructor(
    private val localDS: LocalDS<T>
) : WordRepository<T> {

    override fun getWords(): Set<T> {
        return localDS.fetchAll()
    }

    override fun saveWords(model: T) {
        localDS.save(model)
    }

    interface LocalDS<T> {

        fun save(model: T)

        fun fetch(title: String): T

        fun fetchAll(): Set<T>

        fun delete(model: T)

        fun deleteAll()

    }



}