package com.example.bookwordcounter

import android.app.Application
import android.content.Context
import com.example.bookwordcounter.di.AppInjector
import com.example.bookwordcounter.di.DaggerApplicationComponent

class BookWordCounterApp: Application() {


    override fun onCreate() {
        super.onCreate()
        instance = this@BookWordCounterApp
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