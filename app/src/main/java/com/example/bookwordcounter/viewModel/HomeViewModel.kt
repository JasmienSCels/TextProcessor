package com.example.bookwordcounter.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bookwordcounter.common.base.viewModel.BaseViewModel
import com.example.bookwordcounter.models.WordUIM
import com.example.bookwordcounter.models.toUMI
import com.example.common.core.errorHandling.ErrorType
import com.example.domain.model.WordFrequencyDM
import com.example.domain.usecase.LoadBookUseCase
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val loadBookUseCase: LoadBookUseCase
) : BaseViewModel(loadBookUseCase) {

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    private val _words: MutableLiveData<WordUIM> = MutableLiveData()
    private val _errorState: MutableLiveData<ErrorType> = MutableLiveData()

    val isLoading: LiveData<Boolean>
        get() = _isLoading

    val words: LiveData<WordUIM>
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

    private inner class BookObserver : DisposableObserver<WordFrequencyDM?>() {

        override fun onComplete() {
            Log.d(TAG, "onComplete")
            _isLoading.postValue(false)
        }

        override fun onNext(result: WordFrequencyDM) {
            _words.value = result.toUMI()
        }

        override fun onError(e: Throwable) {
            Log.d(TAG, "onError " + e.localizedMessage)
            _isLoading.postValue(false)
            _errorState.postValue(ErrorType.UNKNOWN_ERROR)
        }
    }

    private companion object {
        val TAG = HomeViewModel::class.java.simpleName
    }

}