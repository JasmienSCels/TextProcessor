package com.example.domain.di

import com.example.domain.usecase.FetchBookUseCase
import com.example.domain.usecase.LoadBookUseCase
import dagger.Component

@Component(modules = [DomainModule::class])
interface DomainComponent {

    fun getLoadBookUseCase(): LoadBookUseCase

    fun getFetchBookUseCase(): FetchBookUseCase
}