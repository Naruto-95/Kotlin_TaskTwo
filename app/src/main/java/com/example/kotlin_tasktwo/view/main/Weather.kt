package com.example.kotlin_tasktwo.view.main



data class Weather(
    val city: City = getDefaultCity(),
    val temperature: Int = 5,
    val feelsLike: Int = 1)
private fun getDefaultCity() = City("Москва", 55.0, 37.0)

