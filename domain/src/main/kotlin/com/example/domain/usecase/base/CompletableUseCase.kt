package com.example.domain.usecase.base

import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.observers.DisposableCompletableObserver

/*
    Base CompletableUseCase for when we want to execute an operation without an expected Result
 */
abstract class CompletableUseCase<in Params>(
    private val subscribeOnScheduler: Scheduler,
    private val observeOnScheduler: Scheduler
) : BaseUseCase() {

    abstract fun buildUseCaseCompletable(params: Params? = null): Completable

    fun execute(observer: DisposableCompletableObserver, params: Params? = null) {
        val completable = buildUseCaseCompletableWithSchedulers(params)
        addDisposable(completable.subscribeWith(observer))
    }

    private fun buildUseCaseCompletableWithSchedulers(params: Params?): Completable {
        return buildUseCaseCompletable(params)
            .subscribeOn(subscribeOnScheduler)
            .observeOn(observeOnScheduler)
    }
}