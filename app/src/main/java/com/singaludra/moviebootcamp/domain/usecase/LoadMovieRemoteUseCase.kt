package com.singaludra.moviebootcamp.domain.usecase

import com.singaludra.moviebootcamp.data.source.Resource
import com.singaludra.moviebootcamp.data.source.local.room.dao.MovieDao
import com.singaludra.moviebootcamp.data.source.remote.network.ApiResponse
import com.singaludra.moviebootcamp.domain.IRemoteDataSource
import com.singaludra.moviebootcamp.domain.MovieLoader
import com.singaludra.moviebootcamp.domain.model.Movie
import com.singaludra.moviebootcamp.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class LoadMovieRemoteUseCase constructor(
    private val remoteDataSource: IRemoteDataSource,
    private val movieDao: MovieDao
): MovieLoader {
    override fun load(): Flow<Resource<List<Movie>>> {
        return  flow {
            emit(Resource.Loading())
            when (val apiResponse = remoteDataSource.getAllMovie().first()) {
                is ApiResponse.Success -> {
                    emit(Resource.Success(DataMapper.mapResponsesToDomain(apiResponse.data.results)))
                    movieDao.insertMovieList(DataMapper.mapResponsesToEntity(apiResponse.data.results))
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