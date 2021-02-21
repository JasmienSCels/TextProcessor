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
        return FileRepositoryImpl.CacheEntry(key, File(context.filesDir, key))
    }

    override fun save(key: String, value: FileRepositoryImpl.CacheEntry<File>) {
        TODO("Not yet implemented")
    }

    override fun delete(key: String) {
        TODO("Not yet implemented")
    }

    override fun clear() {
        TODO("Not yet implemented")
    }

//
//    override fun delete(key: String) {
//        TODO("Not yet implemented")
//    }
//
//    override fun clear() {
//        TODO("Not yet implemented")
//    }
//
//
//    override fun save(key: String, value: File) {
//        TODO("Not yet implemented")
//    }
//
//    override fun get(key: String): File? {
//        TODO("Not yet implemented")
//    }

//    override fun save(key: String, value: File) {
//        File(context.filesDir, key)
//    }
//
//    override fun get(key: String): File? {
//        Log.d("AHHHHHH", File(context.filesDir, key).toString())
//        return  File(context.filesDir, key)
//    }


}


