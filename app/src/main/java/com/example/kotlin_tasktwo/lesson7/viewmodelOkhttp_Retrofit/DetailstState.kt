package com.example.kotlin_tasktwo.lesson7.viewmodelOkhttp_Retrofit

import com.example.kotlin_tasktwo.repository.Weather

sealed  class DetailstState {
    object Loading : DetailstState()
    data class Success(val weather: Weather) : DetailstState()
    data class Error(val error: Throwable) : DetailstState()
}