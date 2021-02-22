package com.example.data.dataSource.local

import android.content.Context
import android.util.Log
import com.example.data.repository.FileRepositoryImpl
import io.reactivex.Single
import java.io.File
import javax.inject.Inject


class FileLocalDS @Inject constructor(private val context: Context) :
    FileRepositoryImpl.LocalDS<String, File?> {

    override fun get(key: String): Single<File?> {
        Log.d("AHHHHHH", File(context.filesDir, key).toString())
        return Single.just(File(context.filesDir, key))
    }

    override fun save(key: String, value: File?) {
//       if(value == null)
//         val file = File(context.filesDir, key)


    }

    override fun delete(key: String) {
        TODO("Not yet implemented")
    }

    override fun clear() {
        TODO("Not yet implemented")
    }
}


