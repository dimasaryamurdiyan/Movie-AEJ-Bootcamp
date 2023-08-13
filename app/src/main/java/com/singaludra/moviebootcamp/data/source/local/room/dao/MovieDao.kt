package com.singaludra.moviebootcamp.data.source.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.singaludra.moviebootcamp.data.source.local.entity.MovieEntity

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie")
    suspend fun getMovieList(): List<MovieEntity>

    @Query("SELECT * FROM movie WHERE id = :id")
    suspend fun getMovie(id: Int): MovieEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieList(movies: List<MovieEntity>)
}