package com.example.thundr.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.End
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thundr.*
import com.example.thundr.R
import org.intellij.lang.annotations.JdkConstants

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

@Composable
fun ForecastScreen() {
    LazyColumn {
        items(items = data) { item: DayForecast ->
            ForecastRow(item = item)
        }
    }
}

@Composable
private fun ForecastRow(item: DayForecast) {
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
                text = stringResource(id = R.string.forecast_high, item.temp.max),
                style = textStyle,
            )
            Text(
                text = stringResource(id = R.string.forecast_low, item.temp.min),
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

@Preview (
    showSystemUi = true
        )
@Composable
private fun ForecastRowPreview() {
    ForecastRow(item = data[0])
}