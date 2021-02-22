package com.example.data.di

import android.content.Context
import com.example.domain.common.reactiveX.scheduler.SchedulerProvider
import com.example.domain.di.DomainInjector

object DataInjector {

    private lateinit var component: DataComponent

    fun initialise(context: Context, scheduler: SchedulerProvider) {
        component = DaggerDataComponent.builder()
            .dataModule(DataModule(context))
            .build()
        with(component) {
            DomainInjector.initialise(
                getFileRepository(),
                getWordRepository(),
                scheduler
            )
        }
    }
}