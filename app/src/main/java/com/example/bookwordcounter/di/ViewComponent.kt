package com.example.bookwordcounter.di

import com.example.bookwordcounter.common.base.viewModel.ViewModelFactory
import com.example.bookwordcounter.viewModel.HomeViewModel
import com.example.domain.di.DomainComponent
import dagger.Component

@Component(dependencies = [DomainComponent::class])
interface ViewComponent {

    fun getHomeViewModelFactory(): ViewModelFactory<HomeViewModel>

}