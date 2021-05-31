package com.chandra.moviecatalogue.ui.favorite.series

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.chandra.moviecatalogue.data.model.detailtvseries.DetailTVSeriesResponse
import com.chandra.moviecatalogue.data.repository.Repository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteSeriesViewModelTest {


    private lateinit var viewModel: FavoriteSeriesViewModel
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var observerSeries: Observer<PagedList<DetailTVSeriesResponse>>

    @Mock
    private lateinit var seriesPagedList: PagedList<DetailTVSeriesResponse>

    @Before
    fun setUp(){
        viewModel = FavoriteSeriesViewModel(repository)
    }

    @Test
    fun getFavoriteSeries() {
        val dummySeries = seriesPagedList
        Mockito.`when`(dummySeries.size).thenReturn(5)
        val series = MutableLiveData<PagedList<DetailTVSeriesResponse>>()
        series.value = dummySeries

        Mockito.`when`(repository.getFavoriteSeries()).thenReturn(series)
        val seriesEntity = viewModel.getFavoriteSeries().value
        Mockito.verify(repository).getFavoriteSeries()

        assertNotNull(seriesEntity)
        assertEquals(5,seriesEntity?.size)

        viewModel.getFavoriteSeries().observeForever(observerSeries)
        Mockito.verify(observerSeries).onChanged(dummySeries)

    }
}