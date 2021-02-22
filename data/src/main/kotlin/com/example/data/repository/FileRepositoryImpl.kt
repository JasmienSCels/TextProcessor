package com.example.data.repository

import com.example.domain.repository.FileRepository
import io.reactivex.Single
import javax.inject.Inject

class FileRepositoryImpl<T> @Inject constructor(
    private val localDS: LocalDS<String, T>,
    private val remoteDS: RemoteDS<T>
) : FileRepository<Single<T>> {

    override fun getFile(title: String): Single<T> =
        fetchAndCacheFile(title)

    private fun fetchAndCacheFile(title: String): Single<T> {
        return remoteDS.fetch(title)
    }

    data class CacheEntry<T>(
        val key: String,
        val value: T
    )

    interface LocalDS<in Key : Any, T> {
        fun get(key: Key): Single<T>?
        fun save(key: Key, value: T)
        fun delete(key: Key)
        fun clear()
    }

    interface RemoteDS<T> {
        fun fetch(title: String): Single<T>
    }

}