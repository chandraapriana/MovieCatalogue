package com.chandra.moviecatalogue.data.remote

import com.chandra.moviecatalogue.data.model.ListPopularMovieResponse
import com.chandra.moviecatalogue.data.model.ListPopularTVResponse
import com.chandra.moviecatalogue.data.model.detailmovie.DetailMovieResponse
import com.chandra.moviecatalogue.data.model.detailtvseries.DetailTVSeriesResponse
import com.chandra.moviecatalogue.utils.EspressoIdlingResource
import retrofit2.await

class RemoteDataSource(private val apiInterface: ApiInterface) {

    suspend fun getPopularMovie(callback: GetPopularMovieCallback) {
        EspressoIdlingResource.increment()
        apiInterface.getPopularMovie().await().results.let {
            callback.getPopularMovie(it)
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getPopularSeries(callback: GetPopularSeriesCallback) {
        EspressoIdlingResource.increment()
        apiInterface.getPopularSeries().await().results.let {
            callback.getPopularSeries(it)
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getDetailMovie(movieId: String, callback: GetDetailMovieCallback) {
        EspressoIdlingResource.increment()
        apiInterface.getDetailMovie(movieId).await().let {
            callback.getDetailMovie(it)
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getDetailSeries(tvId: String, callback: GetDetailSeriesCallback) {
        EspressoIdlingResource.increment()
        apiInterface.getDetailSeries(tvId).await().let {
            callback.getDetailSeries(it)
            EspressoIdlingResource.decrement()
        }
    }


    interface GetPopularMovieCallback {
        fun getPopularMovie(listMovie: List<ListPopularMovieResponse>)
    }

    interface GetDetailMovieCallback {
        fun getDetailMovie(detailMovie: DetailMovieResponse)
    }

    interface GetPopularSeriesCallback {
        fun getPopularSeries(listSeries: List<ListPopularTVResponse>)
    }

    interface GetDetailSeriesCallback {
        fun getDetailSeries(detailSeries: DetailTVSeriesResponse)
    }
}
