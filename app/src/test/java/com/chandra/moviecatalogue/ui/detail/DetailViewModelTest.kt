package com.chandra.moviecatalogue.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.chandra.moviecatalogue.TestCoroutineRule
import com.chandra.moviecatalogue.data.model.detailmovie.DetailMovieResponse
import com.chandra.moviecatalogue.data.model.detailtvseries.DetailTVSeriesResponse
import com.chandra.moviecatalogue.data.repository.Repository
import com.chandra.moviecatalogue.utils.DataDummy
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest : KoinTest {

    private lateinit var viewModel: DetailViewModel

    private val dataDummyDetailMoviePopular = DataDummy.generateDummyMovies()
    private var dataDummyDetailSeriesPopular = DataDummy.generateDummySeries()

    private var movieId = dataDummyDetailMoviePopular.id
    private var seriesId = dataDummyDetailSeriesPopular.id

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var movieObserver: Observer<DetailMovieResponse>

    @Mock
    private lateinit var seriesObserver: Observer<DetailTVSeriesResponse>

    @Before
    fun setUp() {
        Dispatchers.setMain(testCoroutineRule.testDispatcher)
        viewModel = DetailViewModel(repository)

    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testCoroutineRule.testDispatcher.cleanupTestCoroutines()
    }


    @Test
    fun getMovieById() {
        val movieDummy = MutableLiveData<DetailMovieResponse>()
        movieDummy.postValue(dataDummyDetailMoviePopular)

        `when`(repository.getDetailMovie(movieId.toString())).thenReturn(movieDummy)

        val movieData = viewModel.getDetailMovie(movieId.toString()).value

        assertNotNull(movieData)
        verify(repository).getDetailMovie(movieId.toString())

        assertEquals(dataDummyDetailMoviePopular.id, movieData?.id)
        assertEquals(dataDummyDetailMoviePopular.title, movieData?.title)
        assertEquals(dataDummyDetailMoviePopular.overview, movieData?.overview)
        assertEquals(dataDummyDetailMoviePopular.posterPath, movieData?.posterPath)
        assertEquals(dataDummyDetailMoviePopular.releaseDate, movieData?.releaseDate)

        viewModel.getDetailMovie(movieId.toString()).observeForever(movieObserver)
        verify(movieObserver).onChanged(dataDummyDetailMoviePopular)

    }

    @Test
    fun getSeriesById() {
        val tvShowDummy = MutableLiveData<DetailTVSeriesResponse>()
        tvShowDummy.value = dataDummyDetailSeriesPopular

        `when`(repository.getDetailSeries(seriesId.toString())).thenReturn(tvShowDummy)

        val detailSeries = viewModel.getDetailSeries(seriesId.toString())
        verify(repository).getDetailSeries(seriesId.toString())
        assertNotNull(detailSeries)
        assertEquals(dataDummyDetailSeriesPopular.id, detailSeries.value?.id)
        assertEquals(dataDummyDetailSeriesPopular.genres, detailSeries.value?.genres)
        assertEquals(dataDummyDetailSeriesPopular.posterPath, detailSeries.value?.posterPath)
        assertEquals(dataDummyDetailSeriesPopular.name, detailSeries.value?.name)
        assertEquals(dataDummyDetailSeriesPopular.firstAirDate, detailSeries.value?.firstAirDate)
        assertEquals(dataDummyDetailSeriesPopular.overview, detailSeries.value?.overview)

        viewModel.getDetailSeries(seriesId.toString()).observeForever(seriesObserver)
        verify(seriesObserver).onChanged(dataDummyDetailSeriesPopular)

    }


    @Test
    fun getFavoriteMovieById() {
        `when`(repository.getFavoriteMovieById(any())).thenReturn(1)
        val isMovie = viewModel.getFavMovieById("1")
        assertNotNull(isMovie)

        verify(repository).getFavoriteMovieById(any())

    }

    @Test
    fun getFavoriteSeriesById() {
        `when`(repository.getFavoriteSeriesById(any())).thenReturn(1)
        val isMovie = viewModel.getFavSeriesById("1")
        assertNotNull(isMovie)
        verify(repository).getFavoriteSeriesById(any())
    }

    @Test
    fun insertFavoriteMovie() = runBlockingTest {
        val data = DataDummy.generateDummyMovies()
        viewModel.insertFavoriteMovie(data)
        verify(repository).insertFavoriteMovie(any())
    }

    @Test
    fun insertFavoriteSeries() = runBlockingTest {
        val data = DataDummy.generateDummySeries()
        viewModel.insertFavoriteSeries(data)
        verify(repository).insertFavoriteSeries(any())
    }

    @Test
    fun deleteFavoriteMovie() = runBlockingTest {
        val data = DataDummy.generateDummyMovies()
        viewModel.deleteFavoriteMovie(data)
        verify(repository).deleteFavoriteMovie(any())
    }


    @Test
    fun deleteFavoriteSeries() = runBlockingTest {
        val data = DataDummy.generateDummySeries()

        viewModel.deleteFavoriteSeries(data)
        verify(repository).deleteFavoriteSeries(any())
    }

}