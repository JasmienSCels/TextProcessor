package com.example.textProcessor

import android.app.Application
import android.content.Context
import com.example.textProcessor.di.AppInjector
import com.example.bookwordcounter.di.DaggerApplicationComponent

class TextProcessorApp: Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this@TextProcessorApp
        initDagger()
    }

    private fun initDagger() {
        DaggerApplicationComponent
            .builder()
            .build()
        AppInjector.initialise(context = this)
    }

    companion object {
        lateinit var instance: Context
    }

}