package com.example.domain.usecase.base

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.observers.DisposableObserver

/*
    Base ObservableUseCase for when multiple values will be emitted
 */
abstract class ObservableUseCase<Results, in Params>(
    private val subscribeOnScheduler: Scheduler,
    private val observeOnScheduler: Scheduler

) : BaseUseCase() {

    abstract fun buildUseCaseObservable(params: Params? = null): Observable<Results>

    fun execute(observer: DisposableObserver<Results>, params: Params? = null) {
        val observable = buildUseCaseObservableWithSchedulers(params)
        addDisposable(observable.subscribeWith(observer))
    }

    private fun buildUseCaseObservableWithSchedulers(params: Params?): Observable<Results> {
        return buildUseCaseObservable(params)
            .subscribeOn(subscribeOnScheduler)
            .observeOn(observeOnScheduler)
    }
}