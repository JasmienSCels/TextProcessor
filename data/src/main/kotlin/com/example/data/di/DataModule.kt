package com.example.data.di

import android.content.Context
import com.example.data.dataSource.local.FileLocalDS
import com.example.data.dataSource.remote.FileRemoteDS
import com.example.data.dataSource.remote.apiService.BookApiService
import com.example.data.repository.FileRepositoryImpl
import com.example.domain.common.reactiveX.scheduler.SchedulerProvider
import com.example.domain.repository.FileRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.reactivex.Single
import java.io.File

@Module(includes = [DataModule.BindModule::class])
internal class DataModule(private val context: Context, private val scheduler: SchedulerProvider) {

    @Provides
    fun provideFileLocalDS(): FileRepositoryImpl.LocalDS<String, FileRepositoryImpl.CacheEntry<File>> =
        FileLocalDS(context)

    @Provides
    fun provideUserApiService(): BookApiService = BookApiService(context)

    @Provides
    fun provideFileRemoteDS(): FileRepositoryImpl.RemoteDS<File> = FileRemoteDS(provideUserApiService())

    @Provides
    fun provideFileRepository(): FileRepository<Single<File>> =
        FileRepositoryImpl<File>(FileLocalDS(context), FileRemoteDS(provideUserApiService()))

//    @Provides
//    fun provideSchedulerProvider(): SchedulerProvider = scheduler


    @Module
    interface BindModule {

//        @Binds
//        fun bindFileRemoteDS(service: BookApiService): FileRepositoryImpl.RemoteDS<File>

//        @Binds
//        fun bindFilesRepository(filesRepositoryImpl: FileRepositoryImpl<File>): FileRepository<File>
    }
}