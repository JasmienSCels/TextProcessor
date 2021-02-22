package com.example.data.dataSource.local

import com.example.data.dataSource.local.database.dao.WordDAO
import com.example.data.model.toDM
import com.example.data.model.toLM
import com.example.data.repository.WordRepositoryImpl
import com.example.domain.model.WordFrequencyDM
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WordLocalDS @Inject constructor(
    private val wordDAO: WordDAO) :
    WordRepositoryImpl.LocalDS<WordFrequencyDM> {

    override fun save(model: WordFrequencyDM): Completable {
        val lm = model.toLM()
        wordDAO.insert(lm)
        return Completable.complete()
    }

    override fun fetch(title: String): Single<WordFrequencyDM> {
        TODO("Not yet implemented")
    }

    override fun fetchAll(): Set<WordFrequencyDM> {
       return wordDAO.loadAllWords().map { it.toDM() }.toSet()
    }

    override fun delete(model: WordFrequencyDM) {
        TODO("Not yet implemented")
    }

    override fun deleteAll() {
        TODO("Not yet implemented")
    }

}