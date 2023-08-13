package com.singaludra.moviebootcamp.data.source.composite

import android.util.Log
import com.singaludra.moviebootcamp.data.source.Resource
import com.singaludra.moviebootcamp.domain.MovieLoader
import com.singaludra.moviebootcamp.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieLoaderWithFallbackComposite (
    private val primary: MovieLoader,
    private val fallback: MovieLoader
): MovieLoader {
    override fun load(): Flow<Resource<List<Movie>>> {
        return flow {
            try {
                Log.e("Composite", "load primary")
                primary.load()
            } catch (e: Exception){
                Log.e("Composite", "load fallback")
                fallback.load()
            }
        }
    }

}