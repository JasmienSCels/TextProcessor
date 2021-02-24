package com.example.data.repository

import android.util.Log
import com.example.domain.common.errorHandling.NotCachedException
import com.example.domain.repository.WordRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class WordRepositoryImpl<T> @Inject constructor(
    private val localDS: LocalDS<T>
) : WordRepository<T> {

    override fun loadWords(): Observable<T?> {
        Log.d(TAG, "loads words from db")
        return Observable.create { emitter ->
            try {
                localDS.fetchAll().onEach {
                    emitter.onNext(it)
                }.ifEmpty {
                    emitter.onError(NotCachedException("Data Empty"))
                }
                emitter.onComplete()
            } catch (e: Exception) {
                Log.d(TAG, e.localizedMessage)
                emitter.onError(e)
            }
        }
    }

    override fun saveWord(model: T): Completable {
        return localDS.save(model)
    }

    interface LocalDS<T> {

        fun save(model: T): Completable

        fun fetch(title: String): Single<T>

        fun fetchAll(): Set<T>

        fun delete(model: T)

        fun deleteAll()

    }

    private companion object {
        val TAG = WordRepositoryImpl::class.simpleName
    }

}