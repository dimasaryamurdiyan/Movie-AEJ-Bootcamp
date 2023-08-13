package com.singaludra.moviebootcamp.factories

import com.singaludra.moviebootcamp.domain.MovieLoader
import com.singaludra.moviebootcamp.domain.usecase.LoadMovieRemoteUseCase

class RemoteMovieLoaderFactory {
    companion object {
        fun createRemoteMovieLoader(): MovieLoader {
            return LoadMovieRemoteUseCase(
                RemoteDataSourceFactory.createRemoteDataSource(),
                LocalDataSourceFactory.provideDao()
            )
        }
    }
}