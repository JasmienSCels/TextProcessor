package com.example.data.repository

import com.example.domain.repository.FileRepository
import io.reactivex.Single
import java.net.URI
import java.net.URL
import javax.inject.Inject

class FileRepositoryImpl<T> @Inject constructor(
    private val localDS: LocalDS<String, CacheEntry<T>>,
    private val remoteDS: RemoteDS<T>
) : FileRepository<Single<T>> {

    override fun getFile(url: URL): Single<T> =
       fetchAndCacheFile(url)

    private fun fetchAndCacheFile(url: URL): Single<T> =
        remoteDS.fetch(url)
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
        fun fetch(url: URL): Single<T>
    }

}