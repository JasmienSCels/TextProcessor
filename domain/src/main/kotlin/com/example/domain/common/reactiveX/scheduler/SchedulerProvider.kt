package com.example.domain.common.reactiveX.scheduler

import io.reactivex.Scheduler

interface SchedulerProvider {
    val main: Scheduler
    val io: Scheduler
    val comp: Scheduler
}