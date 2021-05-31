package com.chandra.moviecatalogue.data.local

import androidx.room.TypeConverter
import com.chandra.moviecatalogue.data.model.detailtvseries.Genre
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Converters {
    @TypeConverter
    fun fromGenreList(value: List<Genre>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Genre>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toGenreList(value:String):List<Genre>{
        val gson = Gson()
        val type = object : TypeToken<List<Genre>>() {}.type
        return gson.fromJson(value,type)
    }
}