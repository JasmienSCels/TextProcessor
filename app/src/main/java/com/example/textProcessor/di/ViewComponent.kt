package com.example.textProcessor.di

import com.example.domain.di.DomainComponent
import com.example.textProcessor.common.base.viewModel.ViewModelFactory
import com.example.textProcessor.viewModel.HomeViewModel
import dagger.Component

@Component(dependencies = [DomainComponent::class])
interface ViewComponent {

    fun getHomeViewModelFactory(): ViewModelFactory<HomeViewModel>

}