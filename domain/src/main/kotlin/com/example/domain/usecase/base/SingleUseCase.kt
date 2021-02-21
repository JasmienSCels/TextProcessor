package com.example.domain.usecase.base

import android.util.Log
import com.example.domain.common.reactiveX.scheduler.SchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

/*
    Base SingleUseCase class for Single values to be emitted
 */
abstract class SingleUseCase<Results, in Params>(
    private val subscribeOnScheduler: Scheduler,
    private val observeOnScheduler: Scheduler
): BaseUseCase() {

    abstract fun buildUseCaseSingle(params: Params? = null): Single<Results>

    fun execute(observer: DisposableSingleObserver<Results>, params: Params? = null) {
        val single = buildUseCaseSingleWithSchedulers(params)
        addDisposable(single.subscribeWith(observer))
    }

    private fun buildUseCaseSingleWithSchedulers(params: Params?): Single<Results> {
        return buildUseCaseSingle(params)
            .subscribeOn(subscribeOnScheduler)
            .observeOn(observeOnScheduler)
    }
}