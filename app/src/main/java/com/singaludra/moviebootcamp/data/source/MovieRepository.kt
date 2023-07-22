package com.singaludra.moviebootcamp.data.source

import com.singaludra.moviebootcamp.data.source.remote.RemoteDataSource
import com.singaludra.moviebootcamp.data.source.remote.network.ApiResponse
import com.singaludra.moviebootcamp.domain.model.Movie
import com.singaludra.moviebootcamp.domain.repository.IMovieRepository
import com.singaludra.moviebootcamp.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
): IMovieRepository {
    override fun getAllMovie(): Flow<Resource<List<Movie>>> {
        return  flow {
            emit(Resource.Loading())
            when (val apiResponse = remoteDataSource.getListMovie().first()) {
                is ApiResponse.Success -> {
                    emit(Resource.Success(DataMapper.mapResponsesToDomain(apiResponse.data.results)))
                }
                is ApiResponse.Error -> {
                    emit(Resource.Error(apiResponse.errorMessage))
                }
                is ApiResponse.Empty -> {

                }
            }
        }
    }

}