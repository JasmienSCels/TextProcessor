package com.example.domain.di

import com.example.domain.common.reactiveX.scheduler.SchedulerProvider
import com.example.domain.repository.FileRepository
import java.io.File
import java.util.concurrent.Callable
import javax.inject.Provider

object DomainInjector {

    lateinit var component: DomainComponent

    fun initialise(
        fileRepository: Provider<FileRepository<File>>,
        scheduler: SchedulerProvider
    ) {
        component = DaggerDomainComponent.builder()
            .domainModule(DomainModule(fileRepository, scheduler))
            .build()
    }
}