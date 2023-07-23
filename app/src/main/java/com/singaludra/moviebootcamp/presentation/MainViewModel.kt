package com.singaludra.moviebootcamp.presentation

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.singaludra.moviebootcamp.data.source.Resource
import com.singaludra.moviebootcamp.domain.model.Movie
import com.singaludra.moviebootcamp.domain.usecase.MovieUseCase
import com.singaludra.moviebootcamp.utils.networkconnectivity.ConnectionState
import com.singaludra.moviebootcamp.utils.networkconnectivity.observeConnectivityAsFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val movieUseCase: MovieUseCase,
    @ApplicationContext private val appContext: Context
): ViewModel(){
    private val _movieList = MutableLiveData<Resource<List<Movie>>>()
    val movieList: LiveData<Resource<List<Movie>>> get() = _movieList

    private val _connectionState: MutableStateFlow<ConnectionState?> = MutableStateFlow(null)
    val connectionState: StateFlow<ConnectionState?> = _connectionState


    init {
        getAllMovie()
        observeConnectivity()
    }

    fun getAllMovie(){
        viewModelScope.launch {
            movieUseCase.getAllMovie().collect{
                _movieList.postValue(it)
            }
        }
    }

    // Create a flow to observe connectivity state changes
    fun observeConnectivity() {
        viewModelScope.launch {
            appContext.observeConnectivityAsFlow()
                .onCompletion { _connectionState.value = null }
                .catch { _connectionState.value = ConnectionState.Unavailable }
                .collect { state -> _connectionState.value = state }
        }
    }

}