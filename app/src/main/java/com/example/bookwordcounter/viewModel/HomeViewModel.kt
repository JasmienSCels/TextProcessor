package com.example.bookwordcounter.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bookwordcounter.common.base.viewModel.BaseViewModel
import com.example.bookwordcounter.models.WordUIM
import com.example.bookwordcounter.models.toUMI
import com.example.common.core.errorHandling.ErrorType
import com.example.domain.usecase.LoadBookUseCase
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val loadBookUseCase: LoadBookUseCase
) : BaseViewModel(loadBookUseCase) {

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    private val _words: MutableLiveData<Set<WordUIM>> = MutableLiveData()
    private val _errorState: MutableLiveData<ErrorType> = MutableLiveData()

    val isLoading: LiveData<Boolean>
        get() = _isLoading

    val words: LiveData<Set<WordUIM>>
        get() = _words

    val errorState: LiveData<ErrorType>
        get() = _errorState


    fun loadPosts() {
        _isLoading.postValue(true)
        loadBookUseCase.execute(
            observer = BookObserver(),
            params = "Railway-Children-by-E-Nesbit.txt"
        )
    }

    private fun onFetchSuccess(word: Set<WordUIM>) {
        _isLoading.postValue(false)
        _words.postValue(word)
    }

    private inner class BookObserver : DisposableObserver<LoadBookUseCase.Result>() {

        override fun onComplete() {
           Log.d("onComplete", words.toString())
        }

        override fun onNext(result: LoadBookUseCase.Result) {
            _isLoading.postValue(false)
            when (result) {
                is LoadBookUseCase.Result.Success -> onFetchSuccess(result.wordFrequencyDM.toUMI())
                is LoadBookUseCase.Result.ErrorConnection -> _errorState.postValue(ErrorType.NETWORK_CONNECTION_ERROR)
                is LoadBookUseCase.Result.ErrorUnknown -> _errorState.postValue(ErrorType.UNKNOWN_ERROR)
            }
        }

        override fun onError(e: Throwable) {
            _isLoading.postValue(false)
            _errorState.postValue(ErrorType.UNKNOWN_ERROR)
        }
    }

}