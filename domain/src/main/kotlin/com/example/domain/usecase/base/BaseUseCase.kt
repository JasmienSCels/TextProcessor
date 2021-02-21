package com.example.domain.usecase.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/*
    Base UC to add the add the disposables to the CompositeDisposables
 */
abstract class BaseUseCase()
{
    private val disposables = CompositeDisposable()

    open fun dispose() {
        if (!disposables.isDisposed) disposables.dispose()
    }

    protected fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }
}