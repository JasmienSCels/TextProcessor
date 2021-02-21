package com.example.data.di

import com.example.domain.repository.FileRepository
import dagger.Component
import java.io.File
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class])
interface DataComponent {

    fun getFileRepository(): Provider<FileRepository<File>>

}