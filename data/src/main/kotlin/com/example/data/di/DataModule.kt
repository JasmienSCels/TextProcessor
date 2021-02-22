package com.example.data.di

import android.content.Context
import com.example.data.dataSource.local.FileLocalDS
import com.example.data.dataSource.local.WordLocalDS
import com.example.data.dataSource.local.database.AppDatabase
import com.example.data.dataSource.local.database.dao.WordDAO
import com.example.data.dataSource.remote.FileRemoteDS
import com.example.data.dataSource.remote.apiService.BookApiService
import com.example.data.repository.FileRepositoryImpl
import com.example.data.repository.WordRepositoryImpl
import com.example.domain.common.reactiveX.scheduler.SchedulerProvider
import com.example.domain.model.WordFrequencyDM
import com.example.domain.repository.FileRepository
import com.example.domain.repository.WordRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.reactivex.Single
import java.io.File

@Module()
internal class DataModule(private val context: Context) {

    @Provides
    fun provideFileLocalDS(): FileRepositoryImpl.LocalDS<String, File?> =
        FileLocalDS(context)

    @Provides
    fun provideUserApiService(): BookApiService = BookApiService(context)

    @Provides
    fun provideFileRemoteDS(service: BookApiService): FileRepositoryImpl.RemoteDS<File?> =
        FileRemoteDS(context, service)

    @Provides
    fun provideWordLocalDS(db: AppDatabase, dao:WordDAO): WordRepositoryImpl.LocalDS<WordFrequencyDM> =
        WordLocalDS(db, context)

    @Provides
    fun provideFileRepository(localDS: FileLocalDS, remoteDS: FileRemoteDS): FileRepository<Single<File?>> =
        FileRepositoryImpl<File?>(localDS, remoteDS)

    @Provides
    fun provideWordRepository(localDS: WordLocalDS): WordRepository<WordFrequencyDM> =
        WordRepositoryImpl<WordFrequencyDM>(localDS)

    @Provides
    fun provideDatabase(): AppDatabase = AppDatabase.getInstance(context)

}