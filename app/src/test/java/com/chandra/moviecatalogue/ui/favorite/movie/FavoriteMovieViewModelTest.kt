package com.chandra.moviecatalogue.ui.favorite.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.chandra.moviecatalogue.data.model.detailmovie.DetailMovieResponse
import com.chandra.moviecatalogue.data.repository.Repository
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteMovieViewModelTest {

    private lateinit var viewModel: FavoriteMovieViewModel
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var observerMovie: Observer<PagedList<DetailMovieResponse>>

    @Mock
    private lateinit var moviePagedList: PagedList<DetailMovieResponse>

    @Before
    fun setUp(){
        viewModel = FavoriteMovieViewModel(repository)
    }

    @Test
    fun getFavoriteMovie() {
        val dummyMovie = moviePagedList
        Mockito.`when`(dummyMovie.size).thenReturn(5)
        val movie = MutableLiveData<PagedList<DetailMovieResponse>>()
        movie.value = dummyMovie

        Mockito.`when`(repository.getFavoriteMovie()).thenReturn(movie)
        val movieEntity = viewModel.getFavoriteMovie().value
        Mockito.verify(repository).getFavoriteMovie()

        assertNotNull(movieEntity)
        assertEquals(5,movieEntity?.size)

        viewModel.getFavoriteMovie().observeForever(observerMovie)
        Mockito.verify(observerMovie).onChanged(dummyMovie)

    }
}