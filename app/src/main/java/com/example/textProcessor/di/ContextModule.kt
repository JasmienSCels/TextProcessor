package com.example.textProcessor.di

import android.content.Context
import com.example.textProcessor.TextProcessorApp
import dagger.Module
import dagger.Provides

@Module
object ContextModule {

    @Provides
    @JvmStatic
    internal fun provideContext(): Context = TextProcessorApp.instance
}