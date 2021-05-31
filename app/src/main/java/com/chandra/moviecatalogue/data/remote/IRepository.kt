package com.chandra.moviecatalogue.data.remote

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.chandra.moviecatalogue.data.model.ListPopularMovieResponse
import com.chandra.moviecatalogue.data.model.ListPopularTVResponse
import com.chandra.moviecatalogue.data.model.detailmovie.DetailMovieResponse
import com.chandra.moviecatalogue.data.model.detailtvseries.DetailTVSeriesResponse


interface IRepository {
    fun getPopularMovie(): LiveData<List<ListPopularMovieResponse>>?
    fun getPopularSeries(): LiveData<List<ListPopularTVResponse>>?

    fun getDetailMovie(movieId: String): LiveData<DetailMovieResponse>?
    fun getDetailSeries(tvId: String): LiveData<DetailTVSeriesResponse>?

    fun getFavoriteMovie() : LiveData<PagedList<DetailMovieResponse>>
    fun getFavoriteSeries() : LiveData<PagedList<DetailTVSeriesResponse>>

    fun insertFavoriteSeries(series:DetailTVSeriesResponse)
    fun insertFavoriteMovie(movie:DetailMovieResponse)

    fun deleteFavoriteMovie(movie:DetailMovieResponse)
    fun deleteFavoriteSeries(series: DetailTVSeriesResponse)

    fun getFavoriteMovieById(id:String): Int
    fun getFavoriteSeriesById(id:String): Int

}