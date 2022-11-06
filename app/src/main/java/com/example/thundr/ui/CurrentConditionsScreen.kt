package com.example.thundr.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thundr.R

@Composable
fun CurrentConditions(
    onForecastButtonClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
            Text(
                text = stringResource(R.string.city_name),
                style = TextStyle(
                    fontWeight = FontWeight(600),
                    fontSize = 24.sp
                )
            )
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier.padding(horizontal = 32.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.current_temp, 74.0),
                    style = TextStyle(
                        fontWeight = FontWeight(450),
                        fontSize = 72.sp
                    ),
                )
                Text(
                    text = stringResource(R.string.feels_like, 78.0),
                    style = TextStyle(
                        fontSize = 12.sp
                    )
                )
            }
            Spacer(modifier = Modifier.weight(1f, fill = true))
            Image(
                modifier = Modifier.size(95.dp),
                painter = painterResource(R.drawable.sun),
                contentDescription = "Sunny"
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
        ) {
            val textStyle = TextStyle(
                fontSize = 16.sp,
            )
            Text(
                text = stringResource(R.string.low_temp, 71.0),
                style = textStyle
            )
            Text(
                text = stringResource(R.string.high_temp, 84.0),
                style = textStyle
            )
            Text(
                text = stringResource(R.string.humidity, 89.0),
                style = textStyle
            )
            Text(
                text = stringResource(R.string.pressure, 1023),
                style = textStyle
            )
        }
        Spacer(modifier = Modifier.height(72.dp))
        Button(onClick = onForecastButtonClick) {
            Text(text = stringResource(id = R.string.forecast_banner))
        }
    }
}


@Preview(
    showSystemUi = true
)
@Composable
fun CurrentConditionsPreview() {
    CurrentConditions {}
}