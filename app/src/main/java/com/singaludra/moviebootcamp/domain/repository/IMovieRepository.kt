package com.singaludra.moviebootcamp.domain.repository

import com.singaludra.moviebootcamp.data.source.remote.Resource
import com.singaludra.moviebootcamp.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getAllMovie(): Flow<Resource<List<Movie>>>
}