package com.singaludra.moviebootcamp.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.singaludra.moviebootcamp.data.source.Resource
import com.singaludra.moviebootcamp.factories.createLoadMovieLocalUseCaseFactory
import com.singaludra.moviebootcamp.factories.createLoadMovieRemoteUseCaseFactory
import com.singaludra.moviebootcamp.factories.createMovieLoaderWithFallbackCompositeFactory
import com.singaludra.moviebootcamp.domain.MovieLoader
import com.singaludra.moviebootcamp.domain.model.Movie
import com.singaludra.moviebootcamp.utils.networkconnectivity.ConnectionState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel constructor(
    private val movieLoader: MovieLoader,
): ViewModel(){
    private val _movieList = MutableLiveData<Resource<List<Movie>>>()
    val movieList: LiveData<Resource<List<Movie>>> get() = _movieList

    private val _connectionState: MutableStateFlow<ConnectionState?> = MutableStateFlow(null)
    val connectionState: StateFlow<ConnectionState?> = _connectionState


    init {
        getAllMovie()
    }
    fun getAllMovie(){
        viewModelScope.launch {
            movieLoader.load().collect{
                Log.d("ViewModel", "getAllMovie2")
                Log.d("ViewModel", it.toString())
                _movieList.postValue(it)
            }
        }
    }


    companion object {
        val FACTORY: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                MainViewModel(
                    createMovieLoaderWithFallbackCompositeFactory(
                        primary = createLoadMovieRemoteUseCaseFactory(),
                        fallback = createLoadMovieLocalUseCaseFactory()
                    )
                )
            }
        }
    }
}