package com.example.domain.repository

import java.net.URI
import java.net.URL

interface FileRepository<T> {

    // fun getLocalFiles(): List<File>

    fun getFile(url: URL): T
//
//    fun deleteFile(file: File)
//
//    fun deleteAllFiles()

}