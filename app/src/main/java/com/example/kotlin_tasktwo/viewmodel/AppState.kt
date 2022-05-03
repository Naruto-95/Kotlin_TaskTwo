package com.example.kotlin_tasktwo.viewmodel


import com.example.kotlin_tasktwo.repository.Weather


sealed class AppState{


    object Loading : AppState()
    data class Success(val weatherList: List<Weather>) : AppState()
    data class Error(val error: Throwable) : AppState()



}
