package com.example.kotlin_tasktwo.Repository.DTO


data class WeatherDTO(
    val factDTO: FactDTO,
    val forecastDTO: ForecastDTO,
    val infoDTO: InfoDTO,
    val now: Int,
    val now_dt: String
)