package com.chandra.moviecatalogue.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.DataSource
import com.chandra.moviecatalogue.LiveDataTestUtil
import com.chandra.moviecatalogue.PagedListUtil
import com.chandra.moviecatalogue.TestCoroutineRule
import com.chandra.moviecatalogue.data.local.LocalDataSource
import com.chandra.moviecatalogue.data.model.detailmovie.DetailMovieResponse
import com.chandra.moviecatalogue.data.model.detailtvseries.DetailTVSeriesResponse
import com.chandra.moviecatalogue.data.remote.RemoteDataSource
import com.chandra.moviecatalogue.utils.DataDummy
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class RepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private var remoteDataSource = mock(RemoteDataSource::class.java)
    private val localDataSource = mock(LocalDataSource::class.java)

    private lateinit var fakeRepository: FakeRepository

    @Before
    fun setUp() {
        Dispatchers.setMain(testCoroutineRule.testDispatcher)
        fakeRepository = FakeRepository(remoteDataSource, localDataSource)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        reset(localDataSource)
        reset(remoteDataSource)
    }


    @Test
    fun getPopularMovie() {
        val dataDummyMoviePopular = DataDummy.generateDummyListMovies()

        runBlocking {
            doAnswer { invocation ->
                (invocation.arguments[0] as RemoteDataSource.GetPopularMovieCallback).getPopularMovie(
                    dataDummyMoviePopular
                )
                null
            }.`when`(remoteDataSource).getPopularMovie(any())
        }

        val dataMoviePopular = LiveDataTestUtil.getValue(fakeRepository.getPopularMovie())


        runBlocking {
            verify(remoteDataSource).getPopularMovie(any())
        }

        assertNotNull(dataMoviePopular)
        assertEquals(dataDummyMoviePopular.size, dataMoviePopular.size)

    }

    @Test
    fun getPopularSeries() {
        val dataDummySeriesPopular = DataDummy.generateDummyListSeries()

        runBlocking {
            doAnswer { invocation ->
                (invocation.arguments[0] as RemoteDataSource.GetPopularSeriesCallback).getPopularSeries(
                    dataDummySeriesPopular
                )
                null
            }.`when`(remoteDataSource).getPopularSeries(any())
        }

        val dataSeriesPopular =
            fakeRepository.getPopularSeries().let { LiveDataTestUtil.getValue(it) }

        runBlocking {
            verify(remoteDataSource).getPopularSeries(any())
        }

        assertNotNull(dataSeriesPopular)
        assertEquals(
            dataDummySeriesPopular.size,
            dataSeriesPopular.size
        )
    }

    @Test
    fun getDetailMovie() {
        val dataDummyDetailMoviePopular = DataDummy.generateDummyMovies()
        val id = dataDummyDetailMoviePopular.id

        runBlocking {
            doAnswer { invocation ->
                (invocation.arguments[1] as RemoteDataSource.GetDetailMovieCallback).getDetailMovie(
                    dataDummyDetailMoviePopular
                )
                null
            }.`when`(remoteDataSource).getDetailMovie(eq(id.toString()), any())
        }

        val dataDetailMoviePopular =
            fakeRepository.getDetailMovie(id.toString()).let { LiveDataTestUtil.getValue(it) }

        runBlocking {
            verify(remoteDataSource).getDetailMovie(eq(id.toString()), any())
        }

        assertNotNull(dataDetailMoviePopular)
        assertEquals(
            dataDummyDetailMoviePopular.id.toString(),
            dataDetailMoviePopular.id.toString()
        )

    }

    @Test
    fun getDetailSeries() {
        val dataDummyDetailSeriesPopular = DataDummy.generateDummySeries()
        val id = dataDummyDetailSeriesPopular.id

        runBlocking {
            doAnswer { invocation ->
                (invocation.arguments[1] as RemoteDataSource.GetDetailSeriesCallback).getDetailSeries(
                    dataDummyDetailSeriesPopular
                )
                null
            }.`when`(remoteDataSource).getDetailSeries(eq(id.toString()), any())
        }

        val dataDetailSeriesPopular =
            fakeRepository.getDetailSeries(id.toString()).let { LiveDataTestUtil.getValue(it) }

        runBlocking {
            verify(remoteDataSource).getDetailSeries(eq(id.toString()), any())
        }

        assertNotNull(dataDetailSeriesPopular)
        assertEquals(
            dataDummyDetailSeriesPopular.id.toString(),
            dataDetailSeriesPopular.id.toString()
        )
    }

    @Test(expected = NullPointerException::class)
    fun getDetailSeriesNull() {
        val id = ""
        fakeRepository.getDetailSeries(id)
    }

    @Test(expected = NullPointerException::class)
    fun getDetailMovieNull() {
        val id = ""
        fakeRepository.getDetailMovie(id)
    }


    @Test
    fun getFavoriteMovie() {
        val dataDummyMoviePopular = DataDummy.generateDummyListMovies()
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, DetailMovieResponse>
        `when`(localDataSource.getFavoriteMovie()).thenReturn(dataSourceFactory)
        fakeRepository.getFavoriteMovie()

        val favMovieEntity = PagedListUtil.mockPagedList(DataDummy.generateDummyListMovies())
        verify(localDataSource).getFavoriteMovie()
        assertNotNull(favMovieEntity)
        assertEquals(dataDummyMoviePopular.size, favMovieEntity.size)

    }

    @Test
    fun getFavoriteSeries() {
        val dataDummySeriesPopular = DataDummy.generateDummyListSeries()
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, DetailTVSeriesResponse>
        lenient().`when`(localDataSource.getFavoriteSeries()).thenReturn(dataSourceFactory)
        fakeRepository.getFavoriteSeries()

        val favSeriesEntity = PagedListUtil.mockPagedList(DataDummy.generateDummyListSeries())
        verify(localDataSource).getFavoriteSeries()
        assertNotNull(favSeriesEntity)
        assertEquals(dataDummySeriesPopular.size, favSeriesEntity.size)
    }

    @Test
    fun getFavoriteMovieById() {
        `when`(localDataSource.getFavMovieById(any())).thenReturn(1)
        val isMovie = fakeRepository.getFavoriteMovieById("1")
        assertNotNull(isMovie)

        verify(localDataSource).getFavMovieById(any())

    }

    @Test
    fun getFavoriteSeriesById() {
        `when`(localDataSource.getFavSeriesById(any())).thenReturn(1)
        val isMovie = fakeRepository.getFavoriteSeriesById("1")
        assertNotNull(isMovie)
        Mockito.verify(localDataSource).getFavSeriesById(any())
    }

    @Test
    fun insertFavoriteMovie() = testCoroutineRule.testDispatcher.runBlockingTest {
        val data = DataDummy.generateDummyMovies()

        fakeRepository.insertFavoriteMovie(data)
        verify(localDataSource).insertFavoriteMovie(any())

    }


    @Test
    fun insertFavoriteSeries() = testCoroutineRule.testDispatcher.runBlockingTest {
        val data = DataDummy.generateDummySeries()

        fakeRepository.insertFavoriteSeries(data)
        verify(localDataSource).insertFavoriteSeries(any())
    }


    @Test
    fun deleteFavoriteMovie() = testCoroutineRule.testDispatcher.runBlockingTest {
        val data = DataDummy.generateDummyMovies()

        fakeRepository.deleteFavoriteMovie(data)
        verify(localDataSource).deleteFavoriteMovie(any())
    }


    @Test
    fun deleteFavoriteSeries() = testCoroutineRule.testDispatcher.runBlockingTest {
        val data = DataDummy.generateDummySeries()

        fakeRepository.deleteFavoriteSeries(data)
        verify(localDataSource).deleteFavoriteSeries(any())
    }


}