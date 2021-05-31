package com.chandra.moviecatalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.chandra.moviecatalogue.data.model.detailmovie.DetailMovieResponse
import com.chandra.moviecatalogue.data.model.detailtvseries.DetailTVSeriesResponse
import com.chandra.moviecatalogue.data.repository.Repository

class DetailViewModel(private val repository: Repository) : ViewModel() {
    fun getDetailMovie(movieId: String): LiveData<DetailMovieResponse> =
        repository.getDetailMovie(movieId)

    fun getDetailSeries(tvId: String): LiveData<DetailTVSeriesResponse> =
        repository.getDetailSeries(tvId)

    fun insertFavoriteMovie(movie: DetailMovieResponse) = repository.insertFavoriteMovie(movie)
    fun insertFavoriteSeries(series: DetailTVSeriesResponse) =
        repository.insertFavoriteSeries(series)

    fun deleteFavoriteMovie(movie: DetailMovieResponse) = repository.deleteFavoriteMovie(movie)
    fun deleteFavoriteSeries(series: DetailTVSeriesResponse) =
        repository.deleteFavoriteSeries(series)

    fun getFavMovieById(id: String): Int =
        repository.getFavoriteMovieById(id)


    fun getFavSeriesById(id: String): Int =
        repository.getFavoriteSeriesById(id)


}