package com.chandra.moviecatalogue.data.remote

import com.chandra.moviecatalogue.data.model.PopularMovieResponse
import com.chandra.moviecatalogue.data.model.PopularTVResponse
import com.chandra.moviecatalogue.data.model.detailmovie.DetailMovieResponse
import com.chandra.moviecatalogue.data.model.detailtvseries.DetailTVSeriesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {


    @GET("movie/now_playing")
    fun getPopularMovie(): Call<PopularMovieResponse>

    @GET("tv/popular")
    fun getPopularSeries(): Call<PopularTVResponse>

    @GET("movie/{movie_id}")
    fun getDetailMovie(
        @Path("movie_id") movieId: String
    ): Call<DetailMovieResponse>

    @GET("tv/{tv_id}")
    fun getDetailSeries(
        @Path("tv_id") tvId: String
    ): Call<DetailTVSeriesResponse>


}
