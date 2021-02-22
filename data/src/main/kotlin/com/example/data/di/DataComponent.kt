package com.example.data.di

import com.example.domain.model.WordFrequencyDM
import com.example.domain.repository.FileRepository
import com.example.domain.repository.WordRepository
import dagger.Component
import io.reactivex.Single
import java.io.File
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class])
interface DataComponent {

    fun getFileRepository(): Provider<FileRepository<Single<File>>>

    fun getWordRepository(): Provider<WordRepository<WordFrequencyDM>>

}