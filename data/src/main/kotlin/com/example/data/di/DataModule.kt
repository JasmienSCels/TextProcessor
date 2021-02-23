package com.example.data.di

import android.content.Context
import com.example.data.dataSource.local.WordLocalDS
import com.example.data.dataSource.local.database.AppDatabase
import com.example.data.dataSource.local.database.dao.WordDAO
import com.example.data.dataSource.remote.FileRemoteDS
import com.example.data.dataSource.remote.apiService.BookApiService
import com.example.data.repository.FileRepositoryImpl
import com.example.data.repository.WordRepositoryImpl
import com.example.domain.model.WordFrequencyDM
import com.example.domain.repository.FileRepository
import com.example.domain.repository.WordRepository
import dagger.Module
import dagger.Provides
import io.reactivex.Single
import okhttp3.ResponseBody
import java.io.File

@Module
internal class DataModule(private val context: Context) {

    @Provides
    fun provideUserApiService(): BookApiService = BookApiService(context)

    @Provides
    fun provideFileRemoteDS(service: BookApiService): FileRepositoryImpl.RemoteDS<ResponseBody> =
        FileRemoteDS(service)

    @Provides
    fun provideWordLocalDS(dao: WordDAO): WordRepositoryImpl.LocalDS<WordFrequencyDM> =
        WordLocalDS(dao)

    @Provides
    fun provideFileRepository(remoteDS: FileRemoteDS): FileRepository<Single<ResponseBody>> =
        FileRepositoryImpl<ResponseBody>(remoteDS)

    @Provides
    fun provideWordRepository(localDS: WordLocalDS): WordRepository<WordFrequencyDM> =
        WordRepositoryImpl<WordFrequencyDM>(localDS)

    @Provides
    fun provideWordDAO(): WordDAO = AppDatabase.getInstance(context.applicationContext).wordDAO()

}