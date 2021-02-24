package com.example.textProcessor.di

import com.example.domain.di.DomainInjector

object ViewInjector {

    lateinit var component: ViewComponent

    fun initialise() {
        component = DaggerViewComponent.builder()
            .domainComponent(DomainInjector.component)
            .build()
    }
}