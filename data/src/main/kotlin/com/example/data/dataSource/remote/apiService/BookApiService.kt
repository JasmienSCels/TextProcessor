package com.example.data.dataSource.remote.apiService

import android.content.Context
import com.example.data.common.getCommonRetrofit
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.squareup.moshi.Moshi
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import javax.inject.Inject

class BookApiService  @Inject constructor(context: Context) {

    fun loadBook() =
        service.getBook()


    private interface BookService {
      // @Streaming
        @GET("Railway-Children-by-E-Nesbit.txt")
        fun getBook(): Single<ResponseBody>
    }

    private val service by lazy {
        retrofit.create(BookService::class.java)
    }

    private val retrofit: Retrofit by lazy {
        getCommonRetrofit(context)
            .newBuilder()
           // .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addConverterFactory(ScalarsConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    private val moshi: Moshi by lazy {
        Moshi
            .Builder()
            .build()
    }

}