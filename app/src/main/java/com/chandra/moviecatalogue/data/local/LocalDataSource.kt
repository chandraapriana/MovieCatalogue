package com.chandra.moviecatalogue.data.local

import androidx.paging.DataSource
import com.chandra.moviecatalogue.data.model.detailmovie.DetailMovieResponse
import com.chandra.moviecatalogue.data.model.detailtvseries.DetailTVSeriesResponse
import com.chandra.moviecatalogue.utils.EspressoIdlingResource

class LocalDataSource(private val catalogueDao: CatalogueDao) {
    fun getFavoriteMovie(): DataSource.Factory<Int, DetailMovieResponse> {
        EspressoIdlingResource.increment()
        val favMovie = catalogueDao.getFavoriteMovie()
        EspressoIdlingResource.decrement()
        return favMovie
    }

    fun getFavoriteSeries(): DataSource.Factory<Int, DetailTVSeriesResponse> {
        EspressoIdlingResource.increment()
        val favSeries = catalogueDao.getFavoriteSeries()
        EspressoIdlingResource.decrement()
        return favSeries
    }

    fun insertFavoriteSeries(series: DetailTVSeriesResponse) {
        EspressoIdlingResource.increment()
        catalogueDao.insertFavoriteSeries(series)
        EspressoIdlingResource.decrement()
    }


    fun insertFavoriteMovie(movie: DetailMovieResponse) {
        EspressoIdlingResource.increment()
        catalogueDao.insertFavoriteMovie(movie)
        EspressoIdlingResource.decrement()
    }

    fun deleteFavoriteMovie(movie: DetailMovieResponse) {
        EspressoIdlingResource.increment()
        catalogueDao.deleteFavoriteMovies(movie)
        EspressoIdlingResource.decrement()
    }

    fun deleteFavoriteSeries(series: DetailTVSeriesResponse) {
        EspressoIdlingResource.increment()
        catalogueDao.deleteFavoriteSeries(series)
        EspressoIdlingResource.decrement()
    }

    fun getFavMovieById(id: String): Int {
        EspressoIdlingResource.increment()
        val stateFavMovie = catalogueDao.getFavMovieById(id)
        EspressoIdlingResource.decrement()
        return stateFavMovie
    }

    fun getFavSeriesById(id: String): Int {
        EspressoIdlingResource.increment()
        val stateFavSeries = catalogueDao.getFavSeriesById(id)
        EspressoIdlingResource.decrement()
        return stateFavSeries
    }


}