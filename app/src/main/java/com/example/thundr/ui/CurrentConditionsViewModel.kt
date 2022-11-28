package com.example.thundr.ui


import androidx.lifecycle.ViewModel
import com.example.thundr.models.CurrentConditions
import kotlinx.coroutines.channels.Channel
import com.example.thundr.service.OpenWeatherMapAPI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class CurrentConditionsViewModel @Inject constructor(private val api: OpenWeatherMapAPI): ViewModel() {
    private val _currentConditions = Channel<CurrentConditions>()

    public val currentConditions: Flow<CurrentConditions> = _currentConditions.receiveAsFlow()

    fun fetchData() = runBlocking {
        val currentConditions = api.getCurrentConditions("55416")
        _currentConditions.trySend(currentConditions)
    }
}