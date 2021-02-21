package com.example.data.di

import android.content.Context
import com.example.data.dataSource.local.FileLocalDS
import com.example.data.dataSource.remote.FileRemoteDS
import com.example.data.repository.FileRepositoryImpl
import com.example.domain.repository.FileRepository
import dagger.Module
import dagger.Provides
import java.io.File

@Module(includes = [DataModule.BindModule::class])
internal class DataModule(private val context: Context) {


    @Provides
    fun provideFileLocalDS(): FileRepositoryImpl.LocalDS<String, FileRepositoryImpl.CacheEntry<File>> = FileLocalDS(context)

    @Provides
    fun provideFileRemoteDS(): FileRepositoryImpl.RemoteDS<File> = FileRemoteDS()

    @Provides
    fun provideFileRepository(): FileRepository<File> = FileRepositoryImpl<File>(FileLocalDS(context), FileRemoteDS() )


    @Module
    interface BindModule {

//        @Binds
//        fun bindFilesRepository(filesRepositoryImpl: FileRepositoryImpl<File>): FileRepository<File>
    }
}