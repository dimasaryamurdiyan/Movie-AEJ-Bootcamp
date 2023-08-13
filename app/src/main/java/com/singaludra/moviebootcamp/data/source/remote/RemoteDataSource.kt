package com.singaludra.moviebootcamp.data.source.remote

import android.util.Log
import com.singaludra.moviebootcamp.data.source.Resource
import com.singaludra.moviebootcamp.data.source.remote.network.ApiResponse
import com.singaludra.moviebootcamp.data.source.remote.network.ApiService
import com.singaludra.moviebootcamp.data.source.remote.response.GetListMovieResponse
import com.singaludra.moviebootcamp.domain.IRemoteDataSource
import com.singaludra.moviebootcamp.domain.MovieLoader
import com.singaludra.moviebootcamp.domain.model.Movie
import com.singaludra.moviebootcamp.utils.DataMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import retrofit2.http.Query
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService): IRemoteDataSource {

    override fun getAllMovie(): Flow<ApiResponse<GetListMovieResponse>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getListMovie()
                if (response.results != null) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.parse()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    private fun Exception.parse(): String {
        return when(this){
            is IOException -> {
                "You have no connection!"
            }

            is HttpException -> {
                if(this.code() == 422){
                    "Invalid Data"
                } else {
                    "Invalid request"
                }
            }

            else -> {
                this.message.toString()
            }
        }
    }


}