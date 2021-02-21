package com.example.domain.usecase

import com.example.domain.common.reactiveX.scheduler.SchedulerProvider
import com.example.domain.model.WordFrequencyDM
import com.example.domain.usecase.base.SingleUseCase
import io.reactivex.Single
import java.io.File
import javax.inject.Inject

class CalculateWordFrequencyUseCase  @Inject constructor(scheduler: SchedulerProvider) :
    SingleUseCase<List<WordFrequencyDM>, File>(scheduler.comp, scheduler.io) {

    sealed class Result {
        data class Success(val userPostDM: List<WordFrequencyDM>) : Result()
        data class ErrorUnknown(val throwable: Throwable) : Result()
    }


    override fun buildUseCaseSingle(params: File?): Single<List<WordFrequencyDM>> {
        val text = params?.readText()?.toLowerCase()
        val r = Regex("""\p{javaLowerCase}+""")
        val matches = r.findAll(text!!)
        val wordGroups = matches.map { it.value }
            .groupBy { it }
            .map { Pair(it.key, it.value.size) }
           // .sortedByDescending { it.second }
           // .take(10)
        val wordList = mutableListOf<WordFrequencyDM>()
        for ((word, freq) in wordGroups) {
            val wordFrequencyDM = WordFrequencyDM(word = word, frequency = freq, isPrime = null)
            wordList.add(wordFrequencyDM)
        }

        return Single.just(wordList.toList())
    }
}