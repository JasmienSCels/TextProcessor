package com.example.textProcessor.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.textProcessor.common.base.viewModel.BaseViewModel
import com.example.textProcessor.models.WordUIM
import com.example.textProcessor.models.toUMI
import com.example.domain.common.errorHandling.ErrorType
import com.example.domain.common.errorHandling.isConnectionError
import com.example.domain.common.errorHandling.isNotCachedError
import com.example.domain.model.WordFrequencyDM
import com.example.domain.usecase.FetchBookUseCase
import com.example.domain.usecase.LoadBookUseCase
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val loadBookUseCase: LoadBookUseCase,
    private val fetchBookUseCase: FetchBookUseCase
) : BaseViewModel(loadBookUseCase) {

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    private val _words: MutableLiveData<WordUIM> = MutableLiveData()
    private val _errorState: MutableLiveData<ErrorType> = MutableLiveData()

    private lateinit var title: String

    val isLoading: LiveData<Boolean>
        get() = _isLoading

    val words: LiveData<WordUIM>
        get() = _words

    val errorState: LiveData<ErrorType>
        get() = _errorState


    fun loadPosts(title: String) {
        this.title = title
        _isLoading.postValue(true)
        loadBookUseCase.execute(
            observer = BookObserver(),
            params = title
        )
    }

    private inner class BookObserver : DisposableObserver<WordFrequencyDM?>() {

        override fun onComplete() {
            Log.d(TAG, "BookObserver: onComplete")
            _isLoading.postValue(false)
        }

        override fun onNext(result: WordFrequencyDM) {
            _words.value = result.toUMI()
        }

        override fun onError(e: Throwable) {
            Log.d(TAG, "BookObserver: onError " + e.localizedMessage)
            _isLoading.postValue(false)
            when {
                e.isConnectionError() -> _errorState.postValue(ErrorType.NETWORK_CONNECTION_ERROR)
                e.isNotCachedError() -> {
                    _errorState.postValue(ErrorType.NOT_CACHED_ERROR)
                    _isLoading.postValue(true)
                    fetchBookUseCase.execute(FetchObserver(), title)
                }
                else -> _errorState.postValue(ErrorType.UNKNOWN_ERROR)
            }
        }
    }

    private inner class FetchObserver : DisposableSingleObserver<Set<WordFrequencyDM>>() {

        override fun onError(e: Throwable) {
            Log.d(TAG, "BookObserver: onError " + e.localizedMessage)
            _isLoading.postValue(false)
            when {
                e.isConnectionError() -> _errorState.postValue(ErrorType.NETWORK_CONNECTION_ERROR)
                else -> _errorState.postValue(ErrorType.UNKNOWN_ERROR)
            }
        }

        override fun onSuccess(t: Set<WordFrequencyDM>) {
            t.forEach {
                _words.value = it.toUMI()
                _isLoading.postValue(false)
            }
        }
    }

    private companion object {
        val TAG = HomeViewModel::class.java.simpleName
    }

}