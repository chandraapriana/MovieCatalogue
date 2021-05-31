package com.chandra.moviecatalogue.data.model


import com.google.gson.annotations.SerializedName

data class ListPopularMovieResponse(

    @SerializedName("id")
    val id: Int,
    @SerializedName("overview")
    val overview: String,

    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("title")
    val title: String,

    @SerializedName("vote_average")
    val voteAverage: Double

)