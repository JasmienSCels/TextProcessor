package com.example.domain.di

import android.content.Context
import com.example.domain.common.reactiveX.scheduler.SchedulerProvider
import com.example.domain.model.WordFrequencyDM
import com.example.domain.repository.FileRepository
import com.example.domain.repository.WordRepository
import io.reactivex.Single
import okhttp3.ResponseBody
import javax.inject.Provider

object DomainInjector {

    lateinit var component: DomainComponent

    fun initialise(
        context: Context,
        fileRepository: Provider<FileRepository<Single<ResponseBody>>>,
        wordRepository: Provider<WordRepository<WordFrequencyDM>>,
        scheduler: SchedulerProvider
    ) {
        component = DaggerDomainComponent.builder()
            .domainModule(DomainModule(context, fileRepository, wordRepository, scheduler))
            .build()
    }
}