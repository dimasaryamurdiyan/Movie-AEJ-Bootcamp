package com.singaludra.moviebootcamp.domain.usecase

import com.singaludra.moviebootcamp.data.source.Resource
import com.singaludra.moviebootcamp.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getAllMovie(): Flow<Resource<List<Movie>>>
}