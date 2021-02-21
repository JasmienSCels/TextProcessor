package com.example.domain.usecase

import com.example.domain.common.reactiveX.scheduler.SchedulerProvider
import com.example.domain.model.WordFrequencyDM
import com.example.domain.usecase.base.ObservableUseCase
import io.reactivex.Observable
import javax.inject.Inject

class CalculateIsPrimeUseCase @Inject constructor(scheduler: SchedulerProvider) :
    ObservableUseCase<CalculateIsPrimeUseCase.Result, List<WordFrequencyDM>>(
        scheduler.comp,
        scheduler.main
    ) {

    sealed class Result {
        data class Success(val userPostDM: List<WordFrequencyDM>) : Result()
        data class ErrorUnknown(val throwable: Throwable) : Result()
    }

    override fun buildUseCaseObservable(list: List<WordFrequencyDM>?): Observable<Result> {
        if (list == null) throw IllegalStateException("Illegal State Exception")

        return Observable.fromIterable(list)
            .map {
                when (it.frequency) {
                    1 -> it.isPrime = false
                    else -> for (i in 2..it.frequency / 2) {
                        if (it.frequency % i == 0) {
                            it.isPrime = false
                        }
                    }
                }
            }
            .map<Result> { Result.Success(list) }
            .onErrorReturn { Result.ErrorUnknown(it) }
    }
}
