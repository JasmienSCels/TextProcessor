package com.example.data.dataSource.remote

import android.util.Log
import com.example.data.dataSource.remote.apiService.BookApiService
import com.example.data.repository.FileRepositoryImpl
import io.reactivex.Single
import okhttp3.ResponseBody
import javax.inject.Inject

class FileRemoteDS @Inject constructor(
    private val service: BookApiService
) :
    FileRepositoryImpl.RemoteDS<ResponseBody> {

    override fun fetch(title: String): Single<ResponseBody> {
        Log.d("AHHHHH", "LOADING")
        return service.loadBook(title)
    }

}

