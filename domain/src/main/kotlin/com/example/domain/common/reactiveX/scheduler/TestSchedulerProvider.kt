package com.example.common.core.reactiveX

import com.example.domain.common.reactiveX.scheduler.SchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class TestSchedulerProvider : SchedulerProvider {

    override val main: Scheduler
        get() = Schedulers.trampoline()

    override val io: Scheduler
        get() = Schedulers.trampoline()

    override val comp: Scheduler
        get() = Schedulers.trampoline()
}