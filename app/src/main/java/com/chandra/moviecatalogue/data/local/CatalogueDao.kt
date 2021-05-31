package com.chandra.moviecatalogue.data.local

import androidx.paging.DataSource
import androidx.room.*
import com.chandra.moviecatalogue.data.model.detailmovie.DetailMovieResponse
import com.chandra.moviecatalogue.data.model.detailtvseries.DetailTVSeriesResponse

@Dao
interface CatalogueDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteMovie(movie: DetailMovieResponse)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteSeries(series: DetailTVSeriesResponse)

    @Delete
    fun deleteFavoriteMovies(movie: DetailMovieResponse)

    @Delete
    fun deleteFavoriteSeries(series: DetailTVSeriesResponse)

    @Query("Select * FROM tbl_movie ORDER BY id ASC")
    fun getFavoriteMovie():DataSource.Factory<Int,DetailMovieResponse>


    @Query("Select * FROM tbl_series ORDER BY id ASC")
    fun getFavoriteSeries():DataSource.Factory<Int,DetailTVSeriesResponse>


    @Query("SELECT count(*) FROM tbl_movie WHERE tbl_movie.id = :id")
    fun getFavMovieById(id:String):Int

    @Query("SELECT count(*) FROM tbl_series WHERE tbl_series.id = :id")
    fun getFavSeriesById(id:String):Int
}