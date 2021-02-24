package com.example.domain.common.errorHandling

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun Throwable.isConnectionError() =
    this.isNetworkException() || this.cause?.isNetworkException() == true

fun Throwable.isHttpException() =
    this is HttpException

fun Throwable.getHttpException(): Int? = when {
    isHttpException() -> (this as HttpException).code()
    else -> null
}

private fun Throwable.isNetworkException() =
    this is ConnectException || this is UnknownHostException || this is SocketTimeoutException


fun Throwable.isNotCachedError() =
    this is NotCachedException