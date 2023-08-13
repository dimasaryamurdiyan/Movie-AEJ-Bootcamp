package com.singaludra.moviebootcamp.factories

import com.singaludra.moviebootcamp.domain.MovieLoader
import com.singaludra.moviebootcamp.data.source.composite.MovieLoaderWithFallbackComposite
import com.singaludra.moviebootcamp.domain.usecase.LoadMovieLocalUseCase
import com.singaludra.moviebootcamp.domain.usecase.LoadMovieRemoteUseCase
import com.singaludra.moviebootcamp.factories.LocalDataSourceFactory
import com.singaludra.moviebootcamp.factories.RemoteDataSourceFactory

fun createLoadMovieRemoteUseCaseFactory(): MovieLoader {
    return LoadMovieRemoteUseCase(
        RemoteDataSourceFactory.createRemoteDataSource(),
        LocalDataSourceFactory.provideDao()
    )
}

fun createLoadMovieLocalUseCaseFactory(): MovieLoader {
    return LoadMovieLocalUseCase(
        LocalDataSourceFactory.provideDao()
    )
}

fun createMovieLoaderWithFallbackCompositeFactory(
    primary: MovieLoader,
    fallback: MovieLoader,
): MovieLoader {
    return MovieLoaderWithFallbackComposite(
        primary = primary,
        fallback = fallback
    )
}