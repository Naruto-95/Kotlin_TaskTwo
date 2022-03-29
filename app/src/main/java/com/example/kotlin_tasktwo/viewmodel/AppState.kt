package com.example.kotlin_tasktwo.viewmodel


import com.example.kotlin_tasktwo.view.main.Weather

sealed class AppState{
    object Loading : AppState()
    data class Success(val weatherData: Weather) : AppState()
    data class Error(val error: Throwable) : AppState()



}
