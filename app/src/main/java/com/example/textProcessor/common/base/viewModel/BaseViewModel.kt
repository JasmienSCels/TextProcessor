package com.example.textProcessor.common.base.viewModel

import androidx.lifecycle.ViewModel
import com.example.domain.usecase.base.BaseUseCase

abstract class BaseViewModel(vararg useCases: BaseUseCase) : ViewModel() {

    private var useCaseList: MutableList<BaseUseCase> = mutableListOf()

    init {
        useCaseList.addAll(useCases)
    }

    override fun onCleared() {
        super.onCleared()
        useCaseList.forEach { it.dispose() }
    }
}