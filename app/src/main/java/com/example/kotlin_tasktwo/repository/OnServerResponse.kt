package com.example.kotlin_tasktwo.repository

import com.example.kotlin_tasktwo.repository.DTO.WeatherDTO

interface OnServerResponse {
    fun onResponse(weather: WeatherDTO)

}