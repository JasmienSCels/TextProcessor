package com.example.domain.di

import com.example.domain.common.reactiveX.scheduler.SchedulerProvider
import com.example.domain.model.WordFrequencyDM
import com.example.domain.repository.FileRepository
import com.example.domain.repository.WordRepository
import io.reactivex.Single
import java.io.File
import java.util.concurrent.Callable
import javax.inject.Provider

object DomainInjector {

    lateinit var component: DomainComponent

    fun initialise(
        fileRepository: Provider<FileRepository<Single<File>>>,
        wordRepository: Provider<WordRepository<WordFrequencyDM>>,
        scheduler: SchedulerProvider
    ) {
        component = DaggerDomainComponent.builder()
            .domainModule(DomainModule(fileRepository, wordRepository, scheduler))
            .build()
    }
}