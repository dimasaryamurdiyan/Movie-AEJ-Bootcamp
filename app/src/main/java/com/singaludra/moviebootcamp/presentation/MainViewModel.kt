package com.singaludra.moviebootcamp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.singaludra.moviebootcamp.data.source.Resource
import com.singaludra.moviebootcamp.domain.model.Movie
import com.singaludra.moviebootcamp.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val movieUseCase: MovieUseCase
): ViewModel(){
    private val _movieList = MutableLiveData<Resource<List<Movie>>>()
    val movieList: LiveData<Resource<List<Movie>>> get() = _movieList

    init {
        getAllMovie()
    }

    fun getAllMovie(){
        viewModelScope.launch {
            movieUseCase.getAllMovie().collect{
                _movieList.postValue(it)
            }
        }
    }

}