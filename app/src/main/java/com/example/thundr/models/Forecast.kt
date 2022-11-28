package com.example.thundr.models

import com.squareup.moshi.Json

data class Forecast(
    @Json(name = "list") val conditions: List<ForecastData>
)

data class ForecastData(
    @Json(name = "dt") val date: Long,
    @Json(name = "sunrise") val sunrise: Long,
    @Json(name = "sunset") val sunset: Long,
    @Json(name = "temp") val conditions: ForecastConditions,
    @Json(name = "weather") val weather: List<ForecastWeather>
)

data class ForecastConditions(
    @Json(name = "min") val minTemperature: Float,
    @Json(name = "max") val maxTemperature: Float,
)

data class ForecastWeather(
    @Json(name = "icon") val iconName: String
)