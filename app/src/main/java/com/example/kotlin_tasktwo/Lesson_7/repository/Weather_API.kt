package com.example.kotlin_tasktwo.Lesson_7.repository

import com.example.kotlin_tasktwo.Repository.DTO.WeatherDTO
import com.example.kotlin_tasktwo.utils.KEY_BUN_LAT
import com.example.kotlin_tasktwo.utils.KEY_BUN_LON
import com.example.kotlin_tasktwo.utils.KEY_YANDEX
import com.example.kotlin_tasktwo.utils.KEY_YANDEX_API
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface Weather_API {
    @GET(KEY_YANDEX)
    fun getWeather(
        @Header(KEY_YANDEX_API)apikay:String,
        @Query(KEY_BUN_LAT)lat:Double,
        @Query(KEY_BUN_LON)lon:Double):Call<WeatherDTO>
}