package com.example.domain.di

import com.example.domain.common.reactiveX.scheduler.SchedulerProvider
import com.example.domain.repository.FileRepository
import dagger.Module
import dagger.Provides
import java.io.File
import java.util.concurrent.Callable
import javax.inject.Provider

@Module
class DomainModule(
    private val repository: Provider<FileRepository<File>>,
    private val scheduler: SchedulerProvider
) {

    @Provides
    fun provideFileRepository(): FileRepository<File> = repository.get()

    @Provides
    fun provideDomainSchedulerProvider(): SchedulerProvider = scheduler

}