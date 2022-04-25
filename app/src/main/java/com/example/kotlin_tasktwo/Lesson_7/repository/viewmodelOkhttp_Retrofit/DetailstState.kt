package com.example.kotlin_tasktwo.Lesson_7.repository.viewmodelOkhttp_Retrofit

import com.example.kotlin_tasktwo.Repository.Weather

sealed  class DetailstState {
    object Loading : DetailstState()
    data class Success(val weather: Weather) : DetailstState()
    data class Error(val error: Throwable) : DetailstState()
}