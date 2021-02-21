package com.example.data.dataSource.local

import android.content.Context
import android.util.Log
import com.example.data.repository.FileRepositoryImpl
import java.io.File
import javax.inject.Inject


class FileLocalDS @Inject constructor(private val context: Context) :
    FileRepositoryImpl.LocalDS<String, FileRepositoryImpl.CacheEntry<File>> {

    override fun get(key: String): FileRepositoryImpl.CacheEntry<File>? {
        Log.d("AHHHHHH", File(context.filesDir, key).toString())
        return null
    }

    override fun save(key: String, value: FileRepositoryImpl.CacheEntry<File>) {
        Log.d("AHHHHHH", "SAVE")
        TODO("Not yet implemented")
    }

    override fun delete(key: String) {
        TODO("Not yet implemented")
    }

    override fun clear() {
        TODO("Not yet implemented")
    }
}


