package com.singaludra.moviebootcamp.data.source.remote.network

import com.singaludra.moviebootcamp.data.source.remote.response.GetListMovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    suspend fun getListMovie(
        @Query("language") language: String? = null,
        @Query("page") page: Int? = null,
        @Query("region") region: String? = null,
    ): GetListMovieResponse
}