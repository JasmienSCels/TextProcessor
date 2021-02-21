package com.example.bookwordcounter.di

import android.content.Context
import com.example.bookwordcounter.common.reactiveX.DefaultSchedulerProvider
import com.example.data.di.DataInjector
import com.example.domain.di.DomainInjector

object AppInjector {

    fun initialise(context: Context) {
        initialiseDependencies(context)
    }

    private fun initialiseDependencies(context: Context) {
        DataInjector.initialise(context, DefaultSchedulerProvider())
    }
}