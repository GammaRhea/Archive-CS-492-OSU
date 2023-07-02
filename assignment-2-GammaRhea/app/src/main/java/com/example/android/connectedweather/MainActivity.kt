package com.example.android.connectedweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.android.volley.toolbox.StringRequest
import com.example.android.connectedweather.data.ForecastJsonAdapter
import com.example.android.connectedweather.data.ForecastPeriod
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.squareup.moshi.Json
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import android.content.Intent

class MainActivity : AppCompatActivity() {
    private val tag = "MainActivity"
    private val apiBaseUrl = "https://api.openweathermap.org/data/2.5"
    private val apikey = "690d0a23bed3f9c1cc6e86aa0553669d"

    private lateinit var requestQueue: RequestQueue

    private lateinit var forecastAdapter: ForecastAdapter

    private lateinit var forecastListRV: RecyclerView
    private lateinit var loadingErrorTV: TextView
    private lateinit var loadingIndicator: CircularProgressIndicator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        forecastListRV = findViewById(R.id.rv_forecast_list)
        loadingErrorTV = findViewById(R.id.tv_loading_error)
        loadingIndicator = findViewById(R.id.loading_indicator)

        forecastAdapter = ForecastAdapter(::onForecastClick)

        forecastListRV.layoutManager = LinearLayoutManager(this)
        forecastListRV.setHasFixedSize(true)
        forecastListRV.adapter = forecastAdapter

        requestQueue = Volley.newRequestQueue(this)

        getForecast("Albany,OR,US", "imperial")
    }

    private fun getForecast(q: String, units: String) {
        val url = "$apiBaseUrl/forecast?q=$q&units=$units&appid=$apikey"

        val moshi = Moshi.Builder()
            .add(ForecastJsonAdapter())
            .addLast(KotlinJsonAdapterFactory())
            .build()
        val jsonAdapter: JsonAdapter<OpenWeatherResults> =
            moshi.adapter(OpenWeatherResults::class.java)

        val req = StringRequest(Request.Method.GET, url,
            {
                Log.d(tag, it)
                val results = jsonAdapter.fromJson(it)
                Log.d(tag, results.toString())
                forecastAdapter.updateForecastList(results?.list)
                loadingIndicator.visibility = View.INVISIBLE
                forecastListRV.visibility = View.VISIBLE
            },
            {
                Log.d(tag, "Error fetching from $url: ${it.message}")
                loadingErrorTV.text = "Unable to fetch data: Check your connection and try again!"
                loadingIndicator.visibility = View.INVISIBLE
                loadingErrorTV.visibility = View.VISIBLE
            })

        loadingIndicator.visibility = View.VISIBLE
        forecastListRV.visibility = View.INVISIBLE
        loadingErrorTV.visibility = View.INVISIBLE

        requestQueue.add(req)
    }

    private fun onForecastClick(forecastPeriod: ForecastPeriod) {
        val intent = Intent(this, IdvForecast::class.java).apply {
            putExtra(EXTRA_FORECAST, forecastPeriod)
        }
        startActivity(intent)
    }

    private data class OpenWeatherResults(
        @Json val list: List<ForecastPeriod>
    )

}