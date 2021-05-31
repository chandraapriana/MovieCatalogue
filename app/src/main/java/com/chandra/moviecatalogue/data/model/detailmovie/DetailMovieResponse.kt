package com.chandra.moviecatalogue.data.model.detailmovie


import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.chandra.moviecatalogue.data.model.detailtvseries.Genre
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "tbl_movie")
data class DetailMovieResponse(
    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    val id: Int = 0,
    @ColumnInfo(name = "genres")
    @SerializedName("genres")
    val genres: List<Genre>,
    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    val overview: String = "",
    @ColumnInfo(name = "popularity")
    @SerializedName("popularity")
    val popularity: Double = 0.0,
    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    val posterPath: String = "",
    @ColumnInfo(name = "release_date")
    @SerializedName("release_date")
    val releaseDate: String = "",
    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title: String = "",
    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    val voteAverage: Double
) : Parcelable