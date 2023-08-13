package com.singaludra.moviebootcamp.domain.usecase

import android.util.Log
import com.singaludra.moviebootcamp.data.source.Resource
import com.singaludra.moviebootcamp.data.source.local.entity.toDomain
import com.singaludra.moviebootcamp.data.source.local.room.dao.MovieDao
import com.singaludra.moviebootcamp.data.source.remote.network.ApiResponse
import com.singaludra.moviebootcamp.domain.MovieLoader
import com.singaludra.moviebootcamp.domain.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class LoadMovieLocalUseCase constructor(
    private val movieDao: MovieDao
): MovieLoader {
    override fun load(): Flow<Resource<List<Movie>>> {
        return flow {
            emit(Resource.Loading())
            try {
                val data = movieDao.getMovieList()
                if (data != null) {
                    emit(Resource.Success(data.map {
                        it.toDomain()
                    }))
                }
            } catch (e : Exception){
                emit(Resource.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}