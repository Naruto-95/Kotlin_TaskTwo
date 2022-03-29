package com.example.kotlin_tasktwo.view.main




interface Repository {
    fun getWeatherFromRemoteSource(): Weather
    fun getWeatherFromLocalSource(): Weather



}