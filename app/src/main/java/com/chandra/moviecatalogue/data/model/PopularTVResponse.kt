package com.chandra.moviecatalogue.data.model


import com.google.gson.annotations.SerializedName

data class PopularTVResponse(

    @SerializedName("results")
    val results: List<ListPopularTVResponse>,

)