package com.chandra.moviecatalogue.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.chandra.moviecatalogue.data.local.LocalDataSource
import com.chandra.moviecatalogue.data.model.ListPopularMovieResponse
import com.chandra.moviecatalogue.data.model.ListPopularTVResponse
import com.chandra.moviecatalogue.data.model.detailmovie.DetailMovieResponse
import com.chandra.moviecatalogue.data.model.detailtvseries.DetailTVSeriesResponse
import com.chandra.moviecatalogue.data.remote.RemoteDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class FakeRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
)  {
    fun getPopularMovie(): LiveData<List<ListPopularMovieResponse>> {
        val listMoviePopular = MutableLiveData<List<ListPopularMovieResponse>>()

        CoroutineScope(IO).launch {
            remoteDataSource.getPopularMovie(object : RemoteDataSource.GetPopularMovieCallback {
                override fun getPopularMovie(listMovie: List<ListPopularMovieResponse>) {
                    listMoviePopular.postValue(listMovie)
                }
            })

        }
        return listMoviePopular
    }

    fun getPopularSeries(): LiveData<List<ListPopularTVResponse>> {
        val listSeriesPopular = MutableLiveData<List<ListPopularTVResponse>>()

        CoroutineScope(IO).launch {
            remoteDataSource.getPopularSeries(object : RemoteDataSource.GetPopularSeriesCallback {
                override fun getPopularSeries(listSeries: List<ListPopularTVResponse>) {
                    listSeriesPopular.postValue(listSeries)
                }
            })

        }

        return listSeriesPopular
    }

    fun getDetailMovie(movieId: String): LiveData<DetailMovieResponse> {
        val detailMoviePopular = MutableLiveData<DetailMovieResponse>()
        if (movieId.isBlank()) {
            throw NullPointerException("Id is not valid")
        }

        CoroutineScope(IO).launch {
            remoteDataSource.getDetailMovie(movieId, object :
                RemoteDataSource.GetDetailMovieCallback {
                override fun getDetailMovie(detailMovie: DetailMovieResponse) {
                    detailMoviePopular.postValue(detailMovie)
                }
            })
        }

        return detailMoviePopular
    }

    fun getDetailSeries(tvId: String): LiveData<DetailTVSeriesResponse> {
        val detailSeriesPopular = MutableLiveData<DetailTVSeriesResponse>()
        if (tvId.isBlank()) {
            throw NullPointerException("Id is not valid")
        }

        CoroutineScope(IO).launch {
            remoteDataSource.getDetailSeries(tvId, object :
                RemoteDataSource.GetDetailSeriesCallback {
                override fun getDetailSeries(detailSeries: DetailTVSeriesResponse) {
                    detailSeriesPopular.postValue(detailSeries)
                }
            })

        }

        return detailSeriesPopular
    }


    fun getFavoriteMovie(): LiveData<PagedList<DetailMovieResponse>> {
        val config = PagedList.Config.Builder().apply {
            setEnablePlaceholders(false)
            setInitialLoadSizeHint(4)
            setPageSize(4)
        }.build()
        return LivePagedListBuilder(localDataSource.getFavoriteMovie(), config).build()
    }

    fun getFavoriteSeries(): LiveData<PagedList<DetailTVSeriesResponse>> {
        val config = PagedList.Config.Builder().apply {
            setEnablePlaceholders(false)
            setInitialLoadSizeHint(4)
            setPageSize(4)
        }.build()
        return LivePagedListBuilder(localDataSource.getFavoriteSeries(), config).build()
    }

    fun insertFavoriteSeries(series: DetailTVSeriesResponse) {


            CoroutineScope(IO).launch {
                localDataSource.insertFavoriteSeries(series)
            }

    }

    fun insertFavoriteMovie(movie: DetailMovieResponse) {

            CoroutineScope(IO).launch {
                localDataSource.insertFavoriteMovie(movie)
            }



    }


    fun deleteFavoriteMovie(movie: DetailMovieResponse) {
        CoroutineScope(IO).launch {
            localDataSource.deleteFavoriteMovie(movie)
        }

    }

    fun deleteFavoriteSeries(series: DetailTVSeriesResponse) {
        CoroutineScope(IO).launch {
            localDataSource.deleteFavoriteSeries(series)
        }

    }

    fun getFavoriteMovieById(id: String):Int = localDataSource.getFavMovieById(id)
    fun getFavoriteSeriesById(id: String):Int = localDataSource.getFavSeriesById(id)



}