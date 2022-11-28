package com.example.thundr.service

import androidx.compose.ui.res.stringResource
import com.example.thundr.R
import com.example.thundr.models.CurrentConditions
import com.example.thundr.models.Forecast
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherMapAPI {

    @GET("data/2.5/weather")
    suspend fun getCurrentConditions(
        @Query("zip") zip: String,
        @Query("appid") apiKey: String = "74e8f09a272400865010c6a84622c2e0",
        @Query("units") units: String = "imperial"
    ) : CurrentConditions

    @GET("data/2.5/forecast/daily")
    suspend fun getForecast(
        @Query("zip") zip: String,
        @Query("appid") apiKey: String = "74e8f09a272400865010c6a84622c2e0",
        @Query("cnt") count: Int = 16,
        @Query("units") units: String = "imperial"
    ): Forecast
}