package com.example.domain.repository

interface WordRepository<T> {

    fun saveWords(model: T)

    fun getWords(): Set<T>
}