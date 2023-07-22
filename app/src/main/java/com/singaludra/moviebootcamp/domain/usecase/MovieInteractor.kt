package com.singaludra.moviebootcamp.domain.usecase

import com.singaludra.moviebootcamp.data.source.Resource
import com.singaludra.moviebootcamp.domain.model.Movie
import com.singaludra.moviebootcamp.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieInteractor @Inject constructor(
    private val movieRepository: IMovieRepository
): MovieUseCase {
    override fun getAllMovie(): Flow<Resource<List<Movie>>> {
        return movieRepository.getAllMovie()
    }
}