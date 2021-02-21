package com.example.bookwordcounter.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bookwordcounter.common.base.viewModel.BaseViewModel
import com.example.common.core.errorHandling.ErrorType
import com.example.domain.usecase.LoadBookUseCase
import io.reactivex.observers.DisposableSingleObserver
import java.net.URI
import java.net.URL
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val loadBookUseCase: LoadBookUseCase
) : BaseViewModel(loadBookUseCase) {

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
   // private val _userPosts: MutableLiveData<List<UserPostUIM>> = MutableLiveData()
    private val _errorState: MutableLiveData<ErrorType> = MutableLiveData()

    val isLoading: LiveData<Boolean>
        get() = _isLoading

//    val userPosts: LiveData<List<UserPostUIM>>
//        get() = _userPosts

    val errorState: LiveData<ErrorType>
        get() = _errorState


    fun loadPosts() {
        _isLoading.postValue(true)
        loadBookUseCase.execute(observer = BookObserver(), params = URL("http://www.loyalbooks.com/download/text/Railway-Children-by-E-Nesbit.txt"))
    }

    private fun onFetchSuccess(userPosts: Any) {
    }

    private inner class BookObserver : DisposableSingleObserver<LoadBookUseCase.Result>() {

        override fun onSuccess(result: LoadBookUseCase.Result) {
            _isLoading.postValue(false)
            when (result) {
                is LoadBookUseCase.Result.Success -> onFetchSuccess(result)
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