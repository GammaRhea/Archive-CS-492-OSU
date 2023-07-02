package com.example.android.lifecycleweather.api

import com.example.android.lifecycleweather.data.FiveDayForecast
import com.example.android.lifecycleweather.data.OpenWeatherCityJsonAdapter
import com.example.android.lifecycleweather.data.OpenWeatherListJsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

interface ForecastService {

    @GET("forecast")
    suspend fun searchForecast(
        @Query("q") city: String?,
        @Query("units") units: String?,
        @Query("apikey") apiKey: String
    ) : FiveDayForecast

    companion object {
        private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
        fun create() : ForecastService {
            val moshi = Moshi.Builder()
                .add(OpenWeatherListJsonAdapter())
                .add(OpenWeatherCityJsonAdapter())
                .addLast(KotlinJsonAdapterFactory())
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
            return retrofit.create(ForecastService::class.java)
        }
    }

}