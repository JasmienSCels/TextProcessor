package com.example.data.repository

import android.net.Uri
import com.example.domain.repository.FileRepository
import java.io.File
import java.net.URI
import javax.inject.Inject

class FileRepositoryImpl<T> @Inject constructor(
    private val localDS: LocalDS<String, CacheEntry<T>>,
    private val remoteDS: RemoteDS<T>
) : FileRepository<T> {

    override fun getFile(uri: URI): T =
        localDS.get(uri.toString())?.value ?: fetchAndCacheFile(uri)


    private fun fetchAndCacheFile(uri: URI): T =
        remoteDS.fetch(uri).also {
            localDS.save(
                key = uri.toString(),
                value = CacheEntry(key = uri.toString(), value = it)
            )
        }

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
        fun fetch(uri:  URI): T
    }

}