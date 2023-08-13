package com.singaludra.moviebootcamp.factories

import com.singaludra.moviebootcamp.data.source.remote.RemoteDataSource
import com.singaludra.moviebootcamp.domain.IRemoteDataSource

class RemoteDataSourceFactory {
    companion object {
        fun createRemoteDataSource(): IRemoteDataSource {
            return RemoteDataSource(
                MovieServiceFactory.createMovieService()
            )
        }
    }
}