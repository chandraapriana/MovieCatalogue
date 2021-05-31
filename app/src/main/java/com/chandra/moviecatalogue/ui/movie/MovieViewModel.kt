package com.chandra.moviecatalogue.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.chandra.moviecatalogue.data.model.ListPopularMovieResponse
import com.chandra.moviecatalogue.data.repository.Repository

class MovieViewModel(private val repository: Repository) : ViewModel() {

  fun getPopularMovie(): LiveData<List<ListPopularMovieResponse>> = repository.getPopularMovie()

}