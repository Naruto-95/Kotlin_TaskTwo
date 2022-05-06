package com.example.kotlin_tasktwo.lesson7

import com.example.kotlin_tasktwo.BuildConfig
import com.example.kotlin_tasktwo.lesson7.viewmodelOkhttp_Retrofit.DetailstState
import com.example.kotlin_tasktwo.lesson7.viewmodelOkhttp_Retrofit.DetailstViewModel
import com.example.kotlin_tasktwo.repository.City
import com.example.kotlin_tasktwo.repository.DTO.WeatherDTO
import com.example.kotlin_tasktwo.utils.ERROR_SERVER
import com.example.kotlin_tasktwo.utils.KEY_YANDEX_DOMEN
import com.example.kotlin_tasktwo.utils.REQUEST_ERROR
import com.example.kotlin_tasktwo.utils.convertDtoToModel
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class DetailstRepositoryReteofitImpl : DetailstRepository {
    // Реализация ретрофита
    override fun getWeatherDetailst(city: City, callbak: DetailstViewModel.Callbak) {
        val weather_API = Retrofit.Builder().apply {
            baseUrl(KEY_YANDEX_DOMEN)
            addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        }.build().create(WeatherApi::class.java)
        /*Thread {
            val response =
                weather_API.getWeather(BuildConfig.WEATHER_API_KEY, city.lat, city.lon).execute()
            if (response.isSuccessful){
                response.body()?.let {
                    val weather = convertDtoToModel(it)
                    weather.city = city
                    callbak.onResponse(weather)
                }

            }

        }.start()*/

        weather_API.getWeather(BuildConfig.WEATHER_API_KEY, city.lat, city.lon)
            .enqueue(object : Callback<WeatherDTO> {
                override fun onResponse(call: Call<WeatherDTO>, response: Response<WeatherDTO>) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            val weather = convertDtoToModel(it)
                            weather.city = city
                            callbak.onResponse(weather)

                        }

                    }else{
                        callbak.onFail(DetailstState.Error(Throwable(ERROR_SERVER)))
                    }
                }

                override fun onFailure(call: Call<WeatherDTO>, t: Throwable) {
                    callbak.onFail(DetailstState.Error(Throwable(REQUEST_ERROR)))
                }

            })


    }


}

