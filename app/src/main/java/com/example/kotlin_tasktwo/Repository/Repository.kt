package com.example.kotlin_tasktwo.Repository


interface Repository {
    fun getWeatherFromRemoteSource(): Weather
    fun getWeatherRussianFromLocalSource(): List<Weather>
    fun getWeatherWorldFromLocalSource(): List<Weather>


}