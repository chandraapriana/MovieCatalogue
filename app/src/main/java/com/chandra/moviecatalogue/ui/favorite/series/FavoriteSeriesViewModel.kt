package com.chandra.moviecatalogue.ui.favorite.series

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.chandra.moviecatalogue.data.model.detailtvseries.DetailTVSeriesResponse
import com.chandra.moviecatalogue.data.repository.Repository

class FavoriteSeriesViewModel(private val repository: Repository): ViewModel() {
    fun getFavoriteSeries(): LiveData<PagedList<DetailTVSeriesResponse>> = repository.getFavoriteSeries()
}