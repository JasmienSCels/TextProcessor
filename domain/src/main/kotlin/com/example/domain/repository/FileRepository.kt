package com.example.domain.repository

interface FileRepository<T> {

    // fun getLocalFiles(): List<File>

    fun getFile(title: String): T
//
//    fun deleteFile(file: File)
//
//    fun deleteAllFiles()

}