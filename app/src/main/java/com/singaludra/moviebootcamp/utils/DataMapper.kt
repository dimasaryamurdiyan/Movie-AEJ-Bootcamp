package com.singaludra.moviebootcamp.utils

import com.singaludra.moviebootcamp.data.source.local.entity.MovieEntity
import com.singaludra.moviebootcamp.data.source.remote.response.GetListMovieResponse
import com.singaludra.moviebootcamp.domain.model.Movie

class DataMapper {
    companion object {
        fun mapResponsesToDomain(input: List<GetListMovieResponse.MovieResponse>): List<Movie> {
          return input.map {
              Movie(
                  adult = it.adult,
                  backdropPath = it.backdropPath,
                  id = it.id,
                  originalLanguage = it.originalLanguage,
                  originalTitle = it.originalTitle,
                  overview = it.overview,
                  popularity = it.popularity,
                  posterPath = it.posterPath,
                  releaseDate = it.releaseDate,
                  title = it.title,
                  video = it.video,
                  voteAverage = it.voteAverage,
                  voteCount = it.voteCount
              )
          }
      }

        fun mapResponsesToEntity(input: List<GetListMovieResponse.MovieResponse>): List<MovieEntity> {
            return input.map {
                MovieEntity(
                    adult = it.adult,
                    backdropPath = it.backdropPath,
                    id = it.id,
                    originalLanguage = it.originalLanguage,
                    originalTitle = it.originalTitle,
                    overview = it.overview,
                    popularity = it.popularity,
                    posterPath = it.posterPath,
                    releaseDate = it.releaseDate,
                    title = it.title,
                    video = it.video,
                    voteAverage = it.voteAverage,
                    voteCount = it.voteCount
                )
            }
        }

    }
}