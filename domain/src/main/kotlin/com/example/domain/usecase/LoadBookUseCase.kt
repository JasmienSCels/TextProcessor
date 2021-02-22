package com.example.domain.usecase

import android.util.Log
import com.example.domain.common.errorHandling.isConnectionError
import com.example.domain.common.reactiveX.scheduler.SchedulerProvider
import com.example.domain.model.WordFrequencyDM
import com.example.domain.repository.FileRepository
import com.example.domain.repository.WordRepository
import com.example.domain.usecase.base.SingleUseCase
import io.reactivex.Single
import java.io.File
import java.util.*
import javax.inject.Inject

class LoadBookUseCase @Inject constructor(
    private val fileRepository: FileRepository<Single<File>>,
    private val wordRepository: WordRepository<WordFrequencyDM>,
    scheduler: SchedulerProvider
) : SingleUseCase<LoadBookUseCase.Result, String>(scheduler.io, scheduler.io) {

    override fun buildUseCaseSingle(title: String?): Single<Result> {
        val words = wordRepository.getWords()
        Log.d("AHHHH", words.toString())

        return fileRepository.getFile(title!!)
            .map { file ->
                val text = file.readText().toLowerCase(Locale.ROOT)
                val r = Regex("""\p{javaLowerCase}+""")
                val matches = r.findAll(text!!)
                val wordGroups = matches.map { it.value }
                    .groupBy { it }
                    .map { Pair(it.key, it.value.size) }
                val wordList = mutableListOf<WordFrequencyDM>()
                for ((word, freq) in wordGroups) {
                    val wordFrequencyDM =
                        WordFrequencyDM(word = word, frequency = freq, isPrime = isPrime(freq))
                    wordRepository.saveWords(wordFrequencyDM)
                    wordList.add(wordFrequencyDM)
                }
                wordList.toList()
            }.map<Result> {

                Result.Success(it)
            }
            .onErrorReturn {
                getErrorResult(it)
            }
    }

    //Todo: Calculate
    private fun isPrime(freq: Int): Boolean = false

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