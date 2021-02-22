package com.example.domain.di

import com.example.domain.common.reactiveX.scheduler.SchedulerProvider
import com.example.domain.model.WordFrequencyDM
import com.example.domain.repository.FileRepository
import com.example.domain.repository.WordRepository
import dagger.Module
import dagger.Provides
import io.reactivex.Single
import java.io.File
import java.util.concurrent.Callable
import javax.inject.Provider

@Module
class DomainModule(
    private val fileRepository: Provider<FileRepository<Single<File>>>,
    private val wordRepository: Provider<WordRepository<WordFrequencyDM>>,
    private val scheduler: SchedulerProvider
) {

    @Provides
    fun provideFileRepository(): FileRepository<Single<File>> = fileRepository.get()

    @Provides
    fun provideWordRepository(): WordRepository<WordFrequencyDM> = wordRepository.get()

    @Provides
    fun provideDomainSchedulerProvider(): SchedulerProvider = scheduler

}