package com.chandra.moviecatalogue.ui.di

import android.app.Application
import androidx.room.Room
import com.chandra.moviecatalogue.data.local.CatalogueDao
import com.chandra.moviecatalogue.data.local.CatalogueDatabase
import com.chandra.moviecatalogue.data.local.LocalDataSource
import com.chandra.moviecatalogue.data.remote.ApiClient
import com.chandra.moviecatalogue.data.remote.RemoteDataSource
import com.chandra.moviecatalogue.data.repository.Repository
import com.chandra.moviecatalogue.ui.detail.DetailViewModel
import com.chandra.moviecatalogue.ui.favorite.movie.FavoriteMovieViewModel
import com.chandra.moviecatalogue.ui.favorite.series.FavoriteSeriesViewModel
import com.chandra.moviecatalogue.ui.movie.MovieViewModel
import com.chandra.moviecatalogue.ui.series.SeriesViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        ApiClient.retrofit
    }

    fun provideDatabase(application: Application): CatalogueDatabase {
        return Room.databaseBuilder(application, CatalogueDatabase::class.java, "catalogue_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideCountriesDao(database: CatalogueDatabase): CatalogueDao {
        return database.catalogueDao()
    }

    single { provideDatabase(androidApplication()) }
    single { provideCountriesDao(get()) }


    single { RemoteDataSource(get()) }

    single { LocalDataSource(get()) }

    single { Repository(get(), get()) }

    viewModel {
        MovieViewModel(get())
    }

    viewModel {
        SeriesViewModel(get())
    }

    viewModel {
        DetailViewModel(get())
    }

    viewModel {
        FavoriteMovieViewModel(get())
    }

    viewModel { FavoriteSeriesViewModel(get()) }


}