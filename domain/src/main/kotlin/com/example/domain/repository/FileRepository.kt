package com.example.domain.repository

interface FileRepository<T> {

    fun getFile(title: String): T

}