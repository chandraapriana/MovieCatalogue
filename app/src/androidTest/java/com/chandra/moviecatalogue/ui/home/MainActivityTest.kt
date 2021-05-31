package com.chandra.moviecatalogue.ui.home

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import com.chandra.moviecatalogue.R
import com.chandra.moviecatalogue.utils.DataDummy
import com.chandra.moviecatalogue.utils.EspressoIdlingResource
import org.junit.*
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class MainActivityTest{
    private val dataDummyMoviePopular = DataDummy.generateDummyListMovies()
    private val dataDummySeriesPopular = DataDummy.generateDummyListSeries()


    private lateinit var instrumentalContext: Context

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        instrumentalContext = InstrumentationRegistry.getInstrumentation().targetContext
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @Test
    fun loadMovies(){
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
            this.dataDummyMoviePopular.size))
    }

    @Test
    fun loadDetailMovie(){
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.detail_tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_tv_year_and_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_img_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_tv_description)).check(matches(isDisplayed()))
    }

    @Test
    fun loadSeries(){
        onView(withText(R.string.series)).perform(click())
        onView(withId(R.id.rv_series)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_series)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dataDummySeriesPopular.size))
    }

    @Test
    fun loadDetailSeries(){
        onView(withText(R.string.series)).perform(click())
        onView(withId(R.id.rv_series)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.detail_tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_tv_year_and_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_img_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_tv_description)).check(matches(isDisplayed()))

    }

    @Test
    fun insertCheckDeleteMovieFavorite(){
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.fab_add)).perform(click())
        pressBack()

        onView(withId(R.id.favorite)).perform(click())
        onView(withId(R.id.rv_fav_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_fav_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(R.id.detail_tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_tv_year_and_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_img_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_tv_description)).check(matches(isDisplayed()))

        onView(withId(R.id.fab_add)).perform(click())

    }

    @Test
    fun insertCheckDeleteSeriesFavorite(){
        onView(withText(R.string.series)).perform(click())
        onView(withId(R.id.rv_series)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_series)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.fab_add)).perform(click())
        pressBack()

        onView(withId(R.id.favorite)).perform(click())
        onView(withText(R.string.fav_series)).perform(click())
        onView(withId(R.id.rv_fav_series)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_fav_series)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(R.id.detail_tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_tv_year_and_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_img_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_tv_description)).check(matches(isDisplayed()))

        onView(withId(R.id.fab_add)).perform(click())
    }


}