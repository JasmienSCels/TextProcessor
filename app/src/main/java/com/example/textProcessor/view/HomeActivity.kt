package com.example.textProcessor.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.bookwordcounter.R
import com.example.bookwordcounter.databinding.HomeActivityBinding
import com.example.domain.common.errorHandling.ErrorType
import com.example.textProcessor.TextProcessorApp
import com.example.textProcessor.di.AppInjector
import com.example.textProcessor.di.ViewInjector
import com.example.textProcessor.models.WordUIM
import com.example.textProcessor.view.list.WordAdapter
import com.example.textProcessor.viewModel.HomeViewModel
import com.google.android.material.snackbar.Snackbar


class HomeActivity : AppCompatActivity() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var dataBinding: HomeActivityBinding
    private lateinit var adapter: WordAdapter
    private var wordList = mutableListOf<WordUIM>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TextProcessorApp()
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
        homeViewModel.loadPosts(TITLE)
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
                handleIncomingWord(it)
            })
        }

    private fun handleIncomingWord(word: WordUIM) {
        wordList.add(word)
        adapter.apply {
            submitList(wordList)
            notifyDataSetChanged()
        }
    }

    private fun handleLoadingStateChange(isLoading: Boolean) =
        if (isLoading) dataBinding.progressBar.visibility =
            View.VISIBLE else dataBinding.progressBar.visibility = View.GONE

    private fun handleError(error: ErrorType) {
        adapter.currentList.clear()
        when (error) {
            ErrorType.NETWORK_CONNECTION_ERROR -> Snackbar.make(
                dataBinding.root,
                R.string.network_error,
                Snackbar.LENGTH_LONG
            ).show()
            ErrorType.NOT_CACHED_ERROR -> Snackbar.make(
                dataBinding.root,
                R.string.data_not_cached_error,
                Snackbar.LENGTH_LONG
            ).show()
            else -> Snackbar.make(
                dataBinding.root,
                R.string.unknown_error,
                Snackbar.LENGTH_LONG
            ).show()
        }
    }

    private fun setUpRecyclerView() {
        with(dataBinding) {
            adapter = WordAdapter()
                .also { recyclerView.adapter = it }
        }
    }

    private companion object {
        val TAG = HomeActivity::class.java.simpleName
        const val TITLE = "Railway-Children-by-E-Nesbit.txt"
    }

}