package com.chandra.moviecatalogue.ui.favorite.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.chandra.moviecatalogue.data.model.detailmovie.DetailMovieResponse
import com.chandra.moviecatalogue.data.repository.Repository

class FavoriteMovieViewModel (private val repository: Repository): ViewModel() {
    fun getFavoriteMovie(): LiveData<PagedList<DetailMovieResponse>> = repository.getFavoriteMovie()
}