package com.singaludra.moviebootcamp.presentation

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.singaludra.moviebootcamp.R
import com.singaludra.moviebootcamp.data.source.Resource
import com.singaludra.moviebootcamp.databinding.ActivityMainBinding
import com.singaludra.moviebootcamp.domain.model.Movie
import com.singaludra.moviebootcamp.presentation.adapter.MovieAdapter
import com.singaludra.moviebootcamp.presentation.base.BaseActivity
import com.singaludra.moviebootcamp.utils.networkconnectivity.ConnectionState
import com.singaludra.moviebootcamp.utils.shortToast
import dagger.hilt.android.AndroidEntryPoint

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var movieAdapter: MovieAdapter

    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, MainViewModel.FACTORY)[MainViewModel::class.java]
        viewModel.getAllMovie()
        onViewBind()
        onViewObserve()
    }

    private fun onViewObserve() {
        viewModel.apply {
            movieList.observe(this@MainActivity) { movie ->
                when(movie) {
                    is Resource.Loading -> {
                        showLoading()
                    }
                    is Resource.Success -> {
                        hideLoading()
                        movieAdapter.submitData(movie.data)

                        //handle empty data
                        binding.viewEmpty.apply {
                            root.visibility = if (movie.data?.isNotEmpty() == true) View.GONE else View.VISIBLE
                            btnRetry.setOnClickListener {
                                this@MainActivity.recreate()
                            }
                        }
                    }
                    is Resource.Error -> {
                        hideLoading()
                        //handle error
                        binding.viewError.apply {
                            root.visibility = View.VISIBLE
                            tvError.text = movie.message ?: getString(R.string.something_wrong)
                            btnRetry.setOnClickListener {
                                this@MainActivity.recreate()
                            }
                        }

                        this@MainActivity.shortToast(movie.message.toString())
                    }
                }
            }
            this@MainActivity.lifecycleScope.launchWhenStarted {
                connectionState.collect{ state ->
                    if(state == ConnectionState.Unavailable) {
                        Snackbar.make(binding.root, "No internet connection!", Snackbar.LENGTH_LONG)
                            .setAction("RELOAD") {
                                this@MainActivity.recreate()
                            }
                            .show()
                    }
                }
            }
        }
    }

    private fun onViewBind() {
        binding.apply {
            movieAdapter = MovieAdapter(object : MovieAdapter.OnClickListener{
                override fun onClickItem(item: Movie) {
                }
            })

            with(rvMovie){
                layoutManager = LinearLayoutManager(this@MainActivity)
                setHasFixedSize(true)
                adapter = movieAdapter
            }

            swipeRefresh.setOnRefreshListener {
                viewModel.getAllMovie()
                swipeRefresh.isRefreshing = false
            }
        }
    }
}