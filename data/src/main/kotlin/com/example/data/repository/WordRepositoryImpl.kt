package com.example.data.repository

import android.util.Log
import com.example.domain.repository.WordRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import java.lang.Exception
import javax.inject.Inject

class WordRepositoryImpl<T> @Inject constructor(
    private val localDS: LocalDS<T>
) : WordRepository<T> {

    override fun getWords(): Observable<T> {
            return Observable.create {emitter ->
                try {
                    localDS.fetchAll().map{
                        Log.d("AHHHH", it.toString())
                        emitter.onNext(it)
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

        fun save(model: T) : Completable

        fun fetch(title: String): Single<T>

        fun fetchAll(): Set<T>

        fun delete(model: T)

        fun deleteAll()

    }

    private companion object {
        val TAG = WordRepositoryImpl::class.simpleName
    }


}