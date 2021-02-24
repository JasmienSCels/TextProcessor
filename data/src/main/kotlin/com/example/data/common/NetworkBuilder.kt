package com.example.data.common

import com.example.data.BuildConfig
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.schedulers.Schedulers
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

private var retrofit: Retrofit? = null
private var okHttp: OkHttpClient? = null

fun getCommonRetrofit(): Retrofit = retrofit ?: initRetrofit()

fun getCommonOkHttp(): OkHttpClient = okHttp ?: initOkHttp()

private fun initOkHttp(): OkHttpClient =
    OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .connectionPool(ConnectionPool(0, 1, TimeUnit.NANOSECONDS))
        .addInterceptor(httpLoggingInterceptor)
        .build()
        .also {
            okHttp = it
            return it
        }


private fun initRetrofit() =
    Retrofit
        .Builder()
        .client(getCommonOkHttp())
        .baseUrl("http://www.loyalbooks.com/download/text/")
        .callbackExecutor(Executors.newSingleThreadExecutor())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .build()
        .also { retrofit = it }

private val httpLoggingInterceptor by lazy {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.apply {
        loggingInterceptor.level = when {
            BuildConfig.DEBUG -> HttpLoggingInterceptor.Level.BASIC
            else -> HttpLoggingInterceptor.Level.NONE
        }
    }
}