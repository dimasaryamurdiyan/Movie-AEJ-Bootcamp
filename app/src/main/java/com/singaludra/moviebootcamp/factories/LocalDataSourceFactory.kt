package com.singaludra.moviebootcamp.factories

import android.app.Application
import androidx.room.Database
import com.singaludra.moviebootcamp.MyApplication
import com.singaludra.moviebootcamp.data.source.local.room.MovieDatabase
import com.singaludra.moviebootcamp.data.source.local.room.dao.MovieDao

class LocalDataSourceFactory {
    companion object {
        fun provideDao(): MovieDao {
            return MovieDatabase.getDatabase(provideApplication()).getMovieDao()
        }

        fun provideApplication(): MyApplication {
            return MyApplication.instance!!
        }
    }
}