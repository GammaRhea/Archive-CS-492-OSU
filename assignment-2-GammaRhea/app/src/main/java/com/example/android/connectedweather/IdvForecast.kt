package com.example.android.connectedweather

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.android.connectedweather.R
import com.example.android.connectedweather.data.ForecastPeriod
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.*

const val EXTRA_FORECAST ="com.example.android.connectedweather.FORECAST_PERIOD"

class IdvForecast : AppCompatActivity() {
    private var forecastPeriod: ForecastPeriod? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_idv_forecast)

        if (intent != null && intent.hasExtra(EXTRA_FORECAST)) {
            forecastPeriod = intent.getSerializableExtra(EXTRA_FORECAST) as ForecastPeriod
        }

        findViewById<TextView>(R.id.tv_forecast_date).text = SimpleDateFormat("MMMM d, h:mm aa", Locale.ENGLISH).format(forecastPeriod!!.dt * 1000L).toString()

        findViewById<TextView>(R.id.tv_low_temp).text = forecastPeriod!!.temp_min.toString() + 'F'
        findViewById<TextView>(R.id.tv_high_temp).text = forecastPeriod!!.temp_max.toString() + 'F'
        findViewById<TextView>(R.id.tv_pop).text = forecastPeriod!!.pop.toString() + "% chance of precipitation"
        findViewById<TextView>(R.id.tv_description).text = forecastPeriod!!.description
    }
}