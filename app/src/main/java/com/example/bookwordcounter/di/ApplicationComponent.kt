package com.example.bookwordcounter.di


import com.example.bookwordcounter.BookWordCounterApp
import dagger.Component

@Component(
    modules = [ContextModule::class]
)
interface ApplicationComponent {
    fun inject(app: BookWordCounterApp?)
}