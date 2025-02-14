package com.example.data.dataSource.remote.apiService

import com.example.data.common.getCommonRetrofit
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Streaming
import javax.inject.Inject

class BookApiService @Inject constructor() {

    fun loadBook(title: String) =
        service.getBook(title)

    private interface BookService {
        @Streaming
        @GET("{title}")
        fun getBook(
            @Path(value = "title") title: String
        ): Single<ResponseBody>
    }

    private val retrofit: Retrofit =
        getCommonRetrofit()
            .newBuilder()
            .addConverterFactory(ScalarsConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    private val service =
        retrofit.create(BookService::class.java)






}