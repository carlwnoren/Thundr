package com.example.thundr

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter


class ForecastAdapter(private val data: List<DayForecast>) : RecyclerView.Adapter<ForecastViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_forecast_item, parent, false)
        return ForecastViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size
}

class ForecastViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val forecastDate: TextView
    private val forecastHigh: TextView
    private val forecastLow: TextView
    private val forecastSunrise: TextView
    private val forecastSunset: TextView
    val resources = view.resources

    init {
        forecastDate = view.findViewById(R.id.forecast_date)
        forecastHigh = view.findViewById(R.id.forecast_high)
        forecastLow = view.findViewById(R.id.forecast_low)
        forecastSunrise = view.findViewById(R.id.forecast_sunrise)
        forecastSunset = view.findViewById(R.id.forecast_sunset)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun bind(data: DayForecast) {
        val dateTimeStamp = data.date
        val formatter = DateTimeFormatter.ofPattern("MMM dd")
        val dateTime = LocalDateTime.ofEpochSecond(dateTimeStamp, 0, ZoneOffset.of("-5"))
        val formattedDate = formatter.format(dateTime)

        val sunriseTime = LocalDateTime.ofEpochSecond(data.sunrise, 0, ZoneOffset.of("-5"))
        val sunsetTime = LocalDateTime.ofEpochSecond(data.sunset, 0, ZoneOffset.of("-5"))
        val timeFormatter = DateTimeFormatter.ofPattern("h:mm a")
        val formattedSunrise = timeFormatter.format(sunriseTime)
        val formattedSunset = timeFormatter.format(sunsetTime)

        forecastDate.text = resources.getString(R.string.forecast_date, formattedDate)
        forecastHigh.text = resources.getString(R.string.forecast_high, data.temp.max)
        forecastLow.text = resources.getString(R.string.forecast_low, data.temp.min)
        forecastSunrise.text = resources.getString(R.string.forecast_sunrise, formattedSunrise)
        forecastSunset.text = resources.getString(R.string.forecast_sunset, formattedSunset)
    }
}