package com.example.kotlin_tasktwo.Lesson_7.repository

import com.example.kotlin_tasktwo.BuildConfig
import com.example.kotlin_tasktwo.Lesson_7.repository.viewmodelOkhttp_Retrofit.DetailstViewModel
import com.example.kotlin_tasktwo.Repository.City
import com.example.kotlin_tasktwo.Repository.DTO.WeatherDTO
import com.example.kotlin_tasktwo.utils.KEY_YANDEX
import com.example.kotlin_tasktwo.utils.KEY_YANDEX_API
import com.example.kotlin_tasktwo.utils.KEY_YANDEX_DOMEN
import com.example.kotlin_tasktwo.utils.convertDtoToModel
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request


class DetailstRepositoryOKHttpImpl:DetailstRepository {
    override fun getWeatherDetailst(city: City,callbak: DetailstViewModel.Callbak) {
        val client = OkHttpClient()
        val buider = Request.Builder()
        buider.addHeader(KEY_YANDEX_API, BuildConfig.WEATHER_API_KEY)
        buider.url( "$KEY_YANDEX_DOMEN${KEY_YANDEX}lat=${city.lat}&lon=${city.lon}")
        val rec =  buider.build()
        val call = client.newCall(rec)
        Thread{
            val response = call.execute()
            if (response.isSuccessful){
                val weatherDTO: WeatherDTO = Gson().fromJson(response.body()!!.string(), WeatherDTO::class.java)
                val weather = convertDtoToModel(weatherDTO)
                weather.city = city
                callbak.onResponse(weather)

            }else{


            }

        }.start()


    }
}