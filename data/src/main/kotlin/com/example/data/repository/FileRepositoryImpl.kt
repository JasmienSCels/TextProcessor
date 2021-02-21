package com.example.data.repository

import com.example.domain.repository.FileRepository
import io.reactivex.Single
import java.net.URI
import javax.inject.Inject

class FileRepositoryImpl<T> @Inject constructor(
    private val localDS: LocalDS<String, CacheEntry<T>>,
    private val remoteDS: RemoteDS<T>
) : FileRepository<Single<T>> {

    override fun getFile(uri: URI): Single<T> =
       fetchAndCacheFile(uri)

    private fun fetchAndCacheFile(uri: URI): Single<T> =
        remoteDS.fetch(uri)
//            .also {
//            localDS.save(
//                key = uri.toString(),
//                value = CacheEntry(key = uri.toString(), value = it)
//            )
//        }

    data class CacheEntry<T>(
        val key: String,
        val value: T
    )

    interface LocalDS<in Key : Any, T> {
        fun get(key: Key): T?
        fun save(key: Key, value: T)
        fun delete(key: Key)
        fun clear()
    }

    interface RemoteDS<T> {
        fun fetch(uri:  URI): Single<T>
    }

}