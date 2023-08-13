package com.singaludra.moviebootcamp.data.source.remote.response

import com.google.gson.annotations.SerializedName
import android.os.Parcelable

import kotlinx.parcelize.Parcelize



data class GetListMovieResponse(
    @SerializedName("page")
    var page: Int,
    @SerializedName("results")
    var results: List<MovieResponse>,
    @SerializedName("total_pages")
    var totalPages: Int,
    @SerializedName("total_results")
    var totalResults: Int
){
    data class MovieResponse(
        @SerializedName("adult")
        var adult: Boolean,
        @SerializedName("backdrop_path")
        var backdropPath: String,
        @SerializedName("id")
        var id: Int,
        @SerializedName("original_language")
        var originalLanguage: String,
        @SerializedName("original_title")
        var originalTitle: String,
        @SerializedName("overview")
        var overview: String,
        @SerializedName("popularity")
        var popularity: Double,
        @SerializedName("poster_path")
        var posterPath: String,
        @SerializedName("release_date")
        var releaseDate: String,
        @SerializedName("title")
        var title: String,
        @SerializedName("video")
        var video: Boolean,
        @SerializedName("vote_average")
        var voteAverage: Double,
        @SerializedName("vote_count")
        var voteCount: Int
    )
}