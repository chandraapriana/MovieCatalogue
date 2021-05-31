package com.chandra.moviecatalogue.data.model.detailtvseries


import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "tbl_series")
data class DetailTVSeriesResponse(
    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    val id: Int = 0,
    @ColumnInfo(name = "first_air_date")
    @SerializedName("first_air_date")
    val firstAirDate: String = "",

    @ColumnInfo(name = "genres")
    @SerializedName("genres")
    val genres: List<Genre>,
    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name: String = "",
    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    val overview: String = "",
    @ColumnInfo(name = "popularity")
    @SerializedName("popularity")
    val popularity: Double = 0.0,
    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    val posterPath: String = "",

    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    val voteAverage: Double

) : Parcelable