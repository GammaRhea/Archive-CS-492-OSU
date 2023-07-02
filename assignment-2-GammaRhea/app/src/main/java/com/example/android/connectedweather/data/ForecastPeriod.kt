package com.example.android.connectedweather.data

import com.squareup.moshi.FromJson
import com.squareup.moshi.Json
import java.io.Serializable

data class ForecastPeriod(
    val dt: Int,
    val pop: Int,
    val temp_max: Int,
    val temp_min: Int,
    val description: String
) : Serializable

data class OpenWeatherJson(
    val dt: Int,
    val pop: Double,
    val main: OpenWeatherMainJson,
    val weather: List<OpenWeatherWeatherJson>
)

data class OpenWeatherMainJson(
    val temp_max: Double,
    val temp_min: Double
)

data class OpenWeatherWeatherJson(
    val description: String
)

class ForecastJsonAdapter {
    @FromJson
    fun forecastFromJson(list: OpenWeatherJson) = ForecastPeriod(
        dt = list.dt,
        pop = (list.pop * 100).toInt(),
        temp_max = list.main.temp_max.toInt(),
        temp_min = list.main.temp_min.toInt(),
        description = list.weather[0].description
    )
}