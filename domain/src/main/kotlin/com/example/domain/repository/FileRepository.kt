package com.example.domain.repository

import android.net.Uri
import java.net.URI

interface FileRepository<T> {

    // fun getLocalFiles(): List<File>

    fun getFile(uri: URI): T
//
//    fun deleteFile(file: File)
//
//    fun deleteAllFiles()

}