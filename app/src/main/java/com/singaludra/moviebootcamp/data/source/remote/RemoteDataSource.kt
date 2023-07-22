package com.singaludra.moviebootcamp.data.source.remote

import android.util.Log
import com.singaludra.moviebootcamp.data.source.remote.network.ApiResponse
import com.singaludra.moviebootcamp.data.source.remote.network.ApiService
import com.singaludra.moviebootcamp.data.source.remote.response.GetListMovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getListMovie(
        language: String? = null,
        page: Int? = null,
        region: String? = null,
    ): Flow<ApiResponse<GetListMovieResponse>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getListMovie(
                    language, page, region
                )
                if (response.results != null) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }


}