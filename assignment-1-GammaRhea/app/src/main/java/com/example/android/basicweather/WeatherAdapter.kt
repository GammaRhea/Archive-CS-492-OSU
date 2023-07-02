package com.example.android.basicweather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {
    private val weatherList: MutableList<WeatherForecast> = mutableListOf()

    override fun getItemCount() = this.weatherList.size

    fun addWeather(forecast: WeatherForecast) {
        this.weatherList.add(forecast)
//        this.notifyItemInserted(0)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.weather_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(this.weatherList[position])
    }



    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val dayTV: TextView = view.findViewById(R.id.tv_weather_day)
        private val textTV: TextView = view.findViewById(R.id.tv_weather_text)
        private val precipTV: TextView = view.findViewById(R.id.tv_weather_precip)
        private val hiTV: TextView = view.findViewById(R.id.tv_weather_hi)
        private val loTV: TextView = view.findViewById(R.id.tv_weather_lo)


        init {
            view.setOnClickListener {
                Snackbar.make(view, weatherList[absoluteAdapterPosition].long, Snackbar.LENGTH_LONG).show()
            }
        }


        fun bind(forecast: WeatherForecast) {
            this.dayTV.text = forecast.day
            this.textTV.text = forecast.short
            this.precipTV.text = forecast.precip.toString() + "% Rain"
            this.hiTV.text = forecast.high.toString() + " Hi"
            this.loTV.text = forecast.low.toString() + " Low"

            // this.longTV.text = forecast.long
        }
    }
}