package com.chandra.moviecatalogue.data.model

import com.google.gson.annotations.SerializedName

data class PopularMovieResponse(

	@field:SerializedName("results")
	val results: List<ListPopularMovieResponse> = listOf(),

)
