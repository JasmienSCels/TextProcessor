package com.example.bookwordcounter.common.base.viewModel

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders

inline fun <reified VM : ViewModel> AppCompatActivity.activityViewModel(viewModelFactory: ViewModelFactory<VM>? = null): Lazy<VM> =
    lazy { ViewModelProviders.of(this, viewModelFactory).get(VM::class.java) }

inline fun <reified VM : ViewModel> Fragment.viewModel(viewModelFactory: ViewModelFactory<VM>? = null): Lazy<VM> =
    lazy { ViewModelProviders.of(this, viewModelFactory).get(VM::class.java) }