package com.example.bookwordcounter.common.reactiveX

import com.example.domain.common.reactiveX.scheduler.SchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class DefaultSchedulerProvider: SchedulerProvider {
    override val main: Scheduler
        get() = AndroidSchedulers.mainThread()
    override val io: Scheduler
        get() = Schedulers.io()
    override val comp: Scheduler
        get() = Schedulers.computation()
}