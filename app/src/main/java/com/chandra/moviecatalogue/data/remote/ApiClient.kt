package com.chandra.moviecatalogue.data.remote

import com.chandra.moviecatalogue.BuildConfig
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {

    private const val BASE_URL = "https://api.themoviedb.org/3/"
    private val httpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val o = chain.request()
            val url: HttpUrl = o.url.newBuilder()
                .addQueryParameter("api_key", BuildConfig.API_KEY).build()
            val request = o.newBuilder()
                .method(o.method, o.body)
                .url(url)
                .build()

            chain.proceed(request)
        }
        .readTimeout(15, TimeUnit.SECONDS)
        .connectTimeout(15, TimeUnit.SECONDS)
        .build()


    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL).client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiInterface::class.java)


}