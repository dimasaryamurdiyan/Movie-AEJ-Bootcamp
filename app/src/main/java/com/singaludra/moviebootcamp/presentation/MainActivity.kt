package com.singaludra.moviebootcamp.presentation

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import com.singaludra.moviebootcamp.R
import com.singaludra.moviebootcamp.data.source.Resource
import com.singaludra.moviebootcamp.databinding.ActivityMainBinding
import com.singaludra.moviebootcamp.domain.model.Movie
import com.singaludra.moviebootcamp.presentation.adapter.MovieAdapter
import com.singaludra.moviebootcamp.presentation.base.BaseActivity
import com.singaludra.moviebootcamp.utils.shortToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var movieAdapter: MovieAdapter

    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
                    }
                    is Resource.Error -> {
                        hideLoading()
                        //handle error

                        this@MainActivity.shortToast(movie.message.toString())
                    }
                }
            }
        }
    }

    private fun onViewBind() {
        binding.apply {
            movieAdapter = MovieAdapter(object : MovieAdapter.OnClickListener{
                override fun onClickItem(item: Movie) {
                    TODO("Not yet implemented")
                }
            })

            with(rvMovie){
                layoutManager = LinearLayoutManager(this@MainActivity)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }
}