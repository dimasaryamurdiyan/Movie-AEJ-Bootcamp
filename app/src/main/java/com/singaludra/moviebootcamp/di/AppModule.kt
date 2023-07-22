package com.singaludra.moviebootcamp.di

import com.singaludra.moviebootcamp.domain.usecase.MovieInteractor
import com.singaludra.moviebootcamp.domain.usecase.MovieUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {

    @Binds
    @ViewModelScoped
    abstract fun provideUseCase(movieInteractor: MovieInteractor): MovieUseCase

}