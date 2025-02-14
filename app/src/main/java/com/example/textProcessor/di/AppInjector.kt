package com.example.textProcessor.di

import android.content.Context
import com.example.data.di.DataInjector
import com.example.textProcessor.common.reactiveX.DefaultSchedulerProvider

object AppInjector {

    fun initialise(context: Context) {
        initialiseDependencies(context)
    }

    private fun initialiseDependencies(context: Context) {
        DataInjector.initialise(context,
            DefaultSchedulerProvider()
        )
    }
}