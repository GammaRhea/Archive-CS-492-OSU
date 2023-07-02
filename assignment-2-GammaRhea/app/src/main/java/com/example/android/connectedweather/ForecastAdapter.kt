package com.example.android.connectedweather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.connectedweather.data.ForecastPeriod
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

//class ForecastAdapter(val forecastPeriods: List<ForecastPeriod>) :
class ForecastAdapter(private val onClick: (ForecastPeriod) -> Unit) :
    RecyclerView.Adapter<ForecastAdapter.ViewHolder>() {
    var forecastPList = listOf<ForecastPeriod>()

    fun updateForecastList(newList: List<ForecastPeriod>?) {
        forecastPList = newList ?: listOf()
        notifyDataSetChanged()
    }

    override fun getItemCount() = forecastPList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.forecast_list_item, parent, false)
        return ViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(forecastPList[position])
    }

    class ViewHolder(view: View, val onCLick: (ForecastPeriod) -> Unit) : RecyclerView.ViewHolder(view) {
        private val dateTV: TextView = itemView.findViewById(R.id.tv_date)
        private val highTempTV: TextView = itemView.findViewById(R.id.tv_high_temp)
        private val lowTempTV: TextView = itemView.findViewById(R.id.tv_low_temp)
        private val popTV: TextView = itemView.findViewById(R.id.tv_pop)

        private var currentForecastPeriod: ForecastPeriod? = null

        init {
            view.setOnClickListener {
                currentForecastPeriod?.let(onCLick)
            }
        }

        fun bind(forecastPeriod: ForecastPeriod) {
            currentForecastPeriod = forecastPeriod

            dateTV.text = SimpleDateFormat("MMMM d, h:mm aa", Locale.ENGLISH).format(forecastPeriod!!.dt * 1000L).toString()
            highTempTV.text = forecastPeriod.temp_max.toString()
            lowTempTV.text = forecastPeriod.temp_min.toString()
            popTV.text = forecastPeriod.pop.toString() + "% \uD83C\uDF27"
        }
    }
}