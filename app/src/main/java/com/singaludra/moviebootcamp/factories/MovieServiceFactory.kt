package com.singaludra.moviebootcamp.factories

import com.singaludra.moviebootcamp.data.source.remote.network.ApiService

class MovieServiceFactory {
    companion object {
        fun createMovieService(): ApiService {
            return NetworkFactory.provideApiService(
                NetworkFactory.provideOkHttpClient(
                    NetworkFactory.providesRequestInterceptor()
                )
            ).create(ApiService::class.java)
        }
    }
}