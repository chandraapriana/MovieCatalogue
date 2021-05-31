package com.chandra.moviecatalogue.ui.series

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.chandra.moviecatalogue.data.model.ListPopularTVResponse
import com.chandra.moviecatalogue.data.repository.Repository

class SeriesViewModel(private val repository: Repository): ViewModel() {

    fun getPopularSeries(): LiveData<List<ListPopularTVResponse>> = repository.getPopularSeries()

}