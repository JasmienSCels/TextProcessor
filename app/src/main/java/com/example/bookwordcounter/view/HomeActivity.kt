package com.example.bookwordcounter.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.bookwordcounter.BookWordCounterApp
import com.example.bookwordcounter.R
import com.example.bookwordcounter.databinding.HomeActivityBinding
import com.example.bookwordcounter.di.AppInjector
import com.example.bookwordcounter.di.ViewInjector
import com.example.bookwordcounter.models.WordUIM
import com.example.bookwordcounter.view.list.WordAdapter
import com.example.bookwordcounter.viewModel.HomeViewModel
import com.example.common.core.errorHandling.ErrorType
import com.google.android.material.snackbar.Snackbar


class HomeActivity : AppCompatActivity() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var dataBinding: HomeActivityBinding
    private lateinit var adapter: WordAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BookWordCounterApp()
        AppInjector.initialise(this.applicationContext)
        ViewInjector.initialise()


        dataBinding = DataBindingUtil.setContentView(
            this,
            R.layout.home_activity
        )
    }

    override fun onStart() {
        super.onStart()
        homeViewModel =
            ViewModelProvider(this, ViewInjector.component.getHomeViewModelFactory()).get(
                HomeViewModel::class.java
            )

        initObservers()
        setUpRecyclerView()
        homeViewModel.loadPosts()
    }

    private fun initObservers() =
        with(homeViewModel) {
            isLoading.observe(this@HomeActivity, Observer {
                handleLoadingStateChange(it)
            })

            errorState.observe(this@HomeActivity, Observer {
                handleError(it)
            })

            words.observe(this@HomeActivity, Observer {
                handleWords(it)
            })
        }

    private fun handleWords(userPosts: List<WordUIM>) {
        adapter.submitList(userPosts)
    }

    private fun handleLoadingStateChange(isLoading: Boolean) =
        if (isLoading) dataBinding.progressBar.visibility =
            View.VISIBLE else dataBinding.progressBar.visibility = View.GONE

    private fun handleError(error: ErrorType) =
        when (error) {
            ErrorType.NETWORK_CONNECTION_ERROR -> Snackbar.make(
                dataBinding.root,
                "Network Error Connection",
                Snackbar.LENGTH_LONG
            ).show()
            ErrorType.UNKNOWN_ERROR -> Snackbar.make(
                dataBinding.root,
                "An unknown error has occurred",
                Snackbar.LENGTH_LONG
            ).show()
            else -> Snackbar.make(
                dataBinding.root,
                "Opps! An error has occurred on our end.",
                Snackbar.LENGTH_LONG
            ).show()
        }

    private fun setUpRecyclerView() {
        with(dataBinding) {
            adapter = WordAdapter().also { recyclerView.adapter = it }
        }
    }


}