package com.chandra.moviecatalogue.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.chandra.moviecatalogue.data.model.detailmovie.DetailMovieResponse
import com.chandra.moviecatalogue.data.model.detailtvseries.DetailTVSeriesResponse

@Database(entities = [DetailMovieResponse::class, DetailTVSeriesResponse::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class CatalogueDatabase : RoomDatabase() {
    abstract fun catalogueDao() : CatalogueDao

        companion object{
            @Volatile
            private var INSTANCE : CatalogueDatabase? = null
            fun getDatabase(context: Context):CatalogueDatabase{
                val tempInstance = INSTANCE
                if (tempInstance!=null){
                    return tempInstance
                }
                synchronized(this){
                    val instance = Room.databaseBuilder(context.applicationContext,CatalogueDatabase::class.java,"catalogue_db").fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                    return instance
                }
            }
        }
}