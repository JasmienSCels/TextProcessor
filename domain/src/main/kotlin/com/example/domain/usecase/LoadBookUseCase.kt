package com.example.domain.usecase

import com.example.domain.common.reactiveX.scheduler.SchedulerProvider
import com.example.domain.model.WordFrequencyDM
import com.example.domain.repository.WordRepository
import com.example.domain.usecase.base.ObservableUseCase
import io.reactivex.Observable
import javax.inject.Inject

class LoadBookUseCase @Inject constructor(
    private val wordRepository: WordRepository<WordFrequencyDM>,
    val scheduler: SchedulerProvider
) : ObservableUseCase<WordFrequencyDM?, String>(scheduler.io, scheduler.main) {


    override fun buildUseCaseObservable(params: String?): Observable<WordFrequencyDM?> {
        return wordRepository.loadWords()
    }

    private companion object {
        val TAG = LoadBookUseCase::class.java.simpleName
    }

}