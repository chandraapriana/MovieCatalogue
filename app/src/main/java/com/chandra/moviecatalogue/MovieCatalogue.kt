package com.chandra.moviecatalogue

import android.app.Application
import com.chandra.moviecatalogue.ui.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class MovieCatalogue: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MovieCatalogue)
            androidLogger()
            modules(appModule)
        }
    }
}