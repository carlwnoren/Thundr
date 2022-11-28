package com.example.thundr.ui

import androidx.lifecycle.ViewModel
import com.example.thundr.models.CurrentConditions
import com.example.thundr.models.Forecast
import kotlinx.coroutines.channels.Channel
import com.example.thundr.service.OpenWeatherMapAPI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.runBlocking
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(private val api: OpenWeatherMapAPI): ViewModel() {
    private val _forecast = Channel<Forecast>()

    public val forecast: Flow<Forecast> = _forecast.receiveAsFlow()

    fun fetchData() = runBlocking {
        val forecast = api.getForecast("55416")
        _forecast.trySend(forecast)
    }
}