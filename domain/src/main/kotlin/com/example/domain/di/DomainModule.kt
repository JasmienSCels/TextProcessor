package com.example.domain.di

import android.content.Context
import com.example.domain.common.reactiveX.scheduler.SchedulerProvider
import com.example.domain.model.WordFrequencyDM
import com.example.domain.repository.FileRepository
import com.example.domain.repository.WordRepository
import dagger.Module
import dagger.Provides
import io.reactivex.Single
import okhttp3.ResponseBody
import javax.inject.Provider

@Module
class DomainModule(
    private val context: Context,
    private val fileRepository: Provider<FileRepository<Single<ResponseBody>>>,
    private val wordRepository: Provider<WordRepository<WordFrequencyDM>>,
    private val scheduler: SchedulerProvider
) {

    @Provides
    fun provideContext(): Context = context

    @Provides
    fun provideFileRepository(): FileRepository<Single<ResponseBody>> = fileRepository.get()

    @Provides
    fun provideWordRepository(): WordRepository<WordFrequencyDM> = wordRepository.get()

    @Provides
    fun provideDomainSchedulerProvider(): SchedulerProvider = scheduler

}