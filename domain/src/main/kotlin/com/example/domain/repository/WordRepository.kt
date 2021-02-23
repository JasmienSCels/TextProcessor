package com.example.domain.repository

import io.reactivex.Completable
import io.reactivex.Observable

interface WordRepository<T> {

    fun saveWord(model: T) : Completable

    fun getWords(): Observable<T>
}