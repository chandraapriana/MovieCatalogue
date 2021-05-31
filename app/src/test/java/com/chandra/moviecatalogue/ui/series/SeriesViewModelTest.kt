package com.chandra.moviecatalogue.ui.series

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.chandra.moviecatalogue.data.model.ListPopularTVResponse
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
class SeriesViewModelTest {
    private lateinit var viewModel: SeriesViewModel

    private val dataDummySeriesPopular = DataDummy.generateDummyListSeries()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var observerSeries: Observer<List<ListPopularTVResponse>>

    @Before
    fun setUp() {
        viewModel = SeriesViewModel(repository)
    }

    @Test
    fun getPopularSeries() {
        val seriesPopular = MutableLiveData<List<ListPopularTVResponse>>()
        seriesPopular.postValue(dataDummySeriesPopular)

        `when`(repository.getPopularSeries()).thenReturn(seriesPopular)

        val listSeriesPopularResponse = viewModel.getPopularSeries().value

        verify(repository).getPopularSeries()
        assertNotNull(listSeriesPopularResponse)
        assertEquals(seriesPopular.value?.size, listSeriesPopularResponse?.size)

        viewModel.getPopularSeries().observeForever(observerSeries)
        verify(observerSeries).onChanged(dataDummySeriesPopular)

    }

}