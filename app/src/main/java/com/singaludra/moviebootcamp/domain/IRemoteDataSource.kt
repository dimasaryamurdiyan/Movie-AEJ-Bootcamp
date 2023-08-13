package com.singaludra.moviebootcamp.domain

import com.singaludra.moviebootcamp.data.source.Resource
import com.singaludra.moviebootcamp.data.source.remote.network.ApiResponse
import com.singaludra.moviebootcamp.data.source.remote.response.GetListMovieResponse
import com.singaludra.moviebootcamp.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IRemoteDataSource  {
    fun getAllMovie(): Flow<ApiResponse<GetListMovieResponse>>
}