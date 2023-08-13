package com.singaludra.moviebootcamp.domain

import com.singaludra.moviebootcamp.data.source.Resource
import com.singaludra.moviebootcamp.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieLoader {
    fun load(): Flow<Resource<List<Movie>>>
}