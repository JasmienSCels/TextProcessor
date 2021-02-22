package com.example.data.repository

import com.example.domain.repository.FileRepository
import io.reactivex.Single
import javax.inject.Inject

class FileRepositoryImpl<T> @Inject constructor(
    private val remoteDS: RemoteDS<T>
) : FileRepository<Single<T>> {

    override fun getFile(title: String): Single<T> =
        fetchAndCacheFile(title)

    private fun fetchAndCacheFile(title: String): Single<T> {
        return remoteDS.fetch(title)
    }

    interface RemoteDS<T> {
        fun fetch(title: String): Single<T>
    }

}