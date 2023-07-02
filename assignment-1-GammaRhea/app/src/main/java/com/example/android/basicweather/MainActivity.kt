package com.example.android.basicweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val coordinatorLayout = findViewById<CoordinatorLayout>(R.id.coordinator_layout)

        val weatherRV = findViewById<RecyclerView>(R.id.rv_weather_forecast)
        weatherRV.layoutManager = LinearLayoutManager(this)
        weatherRV.setHasFixedSize(true)

        val adapter = WeatherAdapter()
        adapter.addWeather(WeatherForecast("Jan 20", 33, 44, 25, "Overcast", "Overcast with a chance of rain"))
        adapter.addWeather(WeatherForecast("Jan 21", 32, 39, 40, "Overcast", "Overcast with a high chance of rain"))
        adapter.addWeather(WeatherForecast("Jan 22", 30, 33, 65, "Snow", "Snow, expect freezing temperatures"))
        adapter.addWeather(WeatherForecast("Jan 23", 28, 35, 80, "Heavy Snow", "Heavy Snowfall that will blanket the ground"))
        adapter.addWeather(WeatherForecast("Jan 24", 34, 38, 100, "Rain", "Rain will sweep through the area all day"))
        adapter.addWeather(WeatherForecast("Jan 25", 35, 39, 85, "Rain", "Rain will begin to taper off in the evening"))
        adapter.addWeather(WeatherForecast("Jan 26", 36, 41, 15, "Sunny", "Bright skies shine as the rain goes away"))
        adapter.addWeather(WeatherForecast("Jan 27", 37, 45, 0, "Sunny", "Bright skies continue from the day previous"))
        adapter.addWeather(WeatherForecast("Jan 28", 33, 42, 25, "Overcast", "Clouds return with cooler weather"))
        adapter.addWeather(WeatherForecast("Jan 29", 32, 38, 35, "Overcast", "Temperatures continue to decline as things get cooler"))
        adapter.addWeather(WeatherForecast("Jan 30", 36, 44, 15, "Sunny", "Sunshine returns once again as a brief reprieve"))
        adapter.addWeather(WeatherForecast("Jan 31", 99, 120, 0, "Apocalypse", "The Final Days are upon us and the sky burns red with malice"))

        weatherRV.adapter = adapter


    }

}