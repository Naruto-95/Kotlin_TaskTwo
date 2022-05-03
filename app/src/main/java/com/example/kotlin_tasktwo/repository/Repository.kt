package com.example.kotlin_tasktwo.repository


interface Repository {
    fun getWeatherFromRemoteSource(): Weather
    fun getWeatherRussianFromLocalSource(): List<Weather>
    fun getWeatherWorldFromLocalSource(): List<Weather>


}