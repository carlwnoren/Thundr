package com.example.thundr.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.thundr.*
import com.example.thundr.R
import com.example.thundr.models.ForecastData

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ForecastScreen(
    viewModel: ForecastViewModel = hiltViewModel()
) {
    val state by viewModel.forecast.collectAsState(null)
    LaunchedEffect(Unit) {
        viewModel.fetchData()
    }
    Scaffold(
        topBar = {AppBar(title = stringResource(R.string.forecast))}
            ){
            state?.let {
                LazyColumn {
                    items(items = it.conditions) { item: ForecastData ->
                        ForecastRow(item = item)
                        Spacer(modifier = Modifier.height(22.dp))
                    }
                }
            }
        }
}

@Composable
private fun ForecastRow(item: ForecastData) {
    Row(
        modifier = Modifier
            .background(Color.White)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val textStyle = TextStyle(
            fontSize = 18.sp,
        )
        Image(painter = painterResource(id = R.drawable.sun), contentDescription = "")
        AsyncImage(
            model = String.format("https://openweathermap.org/img/wn/%s@2x.png", item.weather.firstOrNull()?.iconName),
            contentDescription = "icon"
        )
        Spacer(modifier = Modifier.weight(1f, fill = true))
        Text(
            text = stringResource(id = R.string.forecast_date, item.date.toMonthDay()),
            style = TextStyle(
                fontSize = 21.sp,
            )
        )
        Spacer(modifier = Modifier.weight(1f, fill = true))
        Column {
            Text(
                text = stringResource(id = R.string.forecast_high, item.conditions.maxTemperature),
                style = textStyle,
            )
            Text(
                text = stringResource(id = R.string.forecast_low, item.conditions.minTemperature),
                style = textStyle,
            )
        }
        Spacer(modifier = Modifier.weight(1f, fill = true))
        Column (
            horizontalAlignment = Alignment.End
                ) {
            Text(
                text = stringResource(id = R.string.forecast_sunrise, item.sunrise.toHourMinute()),
                style = textStyle,
            )
            Text(
                text = stringResource(id = R.string.forecast_sunset, item.sunset.toHourMinute()),
                style = textStyle,
            )
        }
    }
}

