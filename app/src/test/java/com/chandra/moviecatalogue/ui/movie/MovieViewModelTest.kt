package com.chandra.moviecatalogue.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.chandra.moviecatalogue.data.model.ListPopularMovieResponse
import com.chandra.moviecatalogue.data.repository.Repository
import com.chandra.moviecatalogue.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {
    private lateinit var viewModel: MovieViewModel
    private val dataDummyMoviePopular = DataDummy.generateDummyListMovies()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var observerMovie: Observer<List<ListPopularMovieResponse>>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(repository)
    }

    @Test
    fun getMovies() {
        val moviePopular = MutableLiveData<List<ListPopularMovieResponse>>()
        moviePopular.postValue(dataDummyMoviePopular)

        `when`(repository.getPopularMovie()).thenReturn(moviePopular)

        val listMoviePopularResponse = viewModel.getPopularMovie().value

        verify(repository).getPopularMovie()
        assertNotNull(listMoviePopularResponse)
        assertEquals(moviePopular.value?.size, listMoviePopularResponse?.size)

        viewModel.getPopularMovie().observeForever(observerMovie)
        verify(observerMovie).onChanged(dataDummyMoviePopular)
    }

}