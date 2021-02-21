package com.example.bookwordcounter.di

import android.content.Context
import com.example.bookwordcounter.BookWordCounterApp
import dagger.Module
import dagger.Provides

@Module
object ContextModule {

    @Provides
    @JvmStatic
    internal fun provideContext(): Context = BookWordCounterApp.instance
}