package com.example.kotlin_tasktwo.Repository

import com.example.kotlin_tasktwo.Repository.DTO.WeatherDTO

interface OnServerResponse {
    fun onResponse(weather: WeatherDTO)

}