package com.example.domain.usecase

import android.net.Uri
import android.util.Log
import com.example.domain.common.reactiveX.scheduler.SchedulerProvider
import com.example.domain.common.errorHandling.isConnectionError
import com.example.domain.model.WordFrequencyDM
import com.example.domain.repository.FileRepository
import com.example.domain.usecase.base.SingleUseCase
import io.reactivex.Single
import java.io.File
import java.net.URI
import java.util.concurrent.Callable
import javax.inject.Inject

class LoadBookUseCase @Inject constructor(
    private val repository: FileRepository<File>,
    scheduler: SchedulerProvider
) : SingleUseCase<LoadBookUseCase.Result, URI>(scheduler.io, scheduler.main) {

    override fun buildUseCaseSingle(uri: URI?): Single<Result> {
//       Callable {  }repository.getFile(uri!!)
//            .map {
//                val text = it.readText().toLowerCase()
//                val r = Regex("""\p{javaLowerCase}+""")
//                val matches = r.findAll(text!!)
//                val wordGroups = matches.map { it.value }
//                    .groupBy { it }
//                    .map { Pair(it.key, it.value.size) }
//                // .sortedByDescending { it.second }
//                // .take(10)
//                val wordList = mutableListOf<WordFrequencyDM>()
//                for ((word, freq) in wordGroups) {
//                    val wordFrequencyDM =
//                        WordFrequencyDM(word = word, frequency = freq, isPrime = null)
//                    wordList.add(wordFrequencyDM)
//                }
//                wordList.toList()
//            }.map<Result> {
//                it.map {
//                    when (it.frequency) {
//                        1 -> it.isPrime = false
//                        else -> for (i in 2..it.frequency / 2) {
//                            if (it.frequency % i == 0) {
//                                it.isPrime = false
//                            }
//                        }
//                    }
//                }
//                Result.Success(it)
//            }
//            .onErrorReturn(::getErrorResult)
        val book = repository.getFile(uri!!)
        Log.d("AHHHH", book.toString())
        return Single.just(Result.Success(emptyList()))
    }


    private fun getErrorResult(throwable: Throwable): Result = when {
        throwable.isConnectionError() -> Result.ErrorConnection
        else -> Result.ErrorUnknown(throwable)
    }

    sealed class Result {
        data class Success(val wordFrequencyList: List<WordFrequencyDM>) : Result()
        object ErrorConnection : Result()
        data class ErrorUnknown(val throwable: Throwable) : Result()
    }
}