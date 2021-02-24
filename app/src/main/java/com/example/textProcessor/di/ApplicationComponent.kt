package com.example.textProcessor.di


import com.example.textProcessor.TextProcessorApp
import dagger.Component

@Component(
    modules = [ContextModule::class]
)
interface ApplicationComponent {
    fun inject(app: TextProcessorApp?)
}