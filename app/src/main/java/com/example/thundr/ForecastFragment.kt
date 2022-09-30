package com.example.thundr

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.thundr.databinding.FragmentForecastBinding
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class ForecastFragment : Fragment(R.layout.fragment_forecast) {
    private lateinit var binding: FragmentForecastBinding


    private val data: List<DayForecast> = (0..15).map {
        DayForecast(
            1664402978 + it * 86400.toLong(),
            1664402978 - 1000 - it * 200.toLong(),
            1664402978 + it * 50.toLong(),
            ForecastTemp(
                42 - it.toFloat(),
                42 + it * 1.2.toFloat()
            ),
            1025 + it.toFloat(),
            80 + it
        ) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentForecastBinding.bind(view)
        binding.forecastList.adapter = ForecastAdapter(data)
    }
}