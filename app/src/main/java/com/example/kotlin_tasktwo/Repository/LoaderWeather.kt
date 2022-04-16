package com.example.kotlin_tasktwo.Repository


import android.os.Handler
import android.os.Looper
import com.example.kotlin_tasktwo.Repository.DTO.WeatherDTO
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection
import com.google.gson.Gson
//2:51
//Доделать погоду в плане показов прогноза городов

class LoaderWeather (private val onServerResponseListener:OnServerResponse){
    fun LoadWeather(lat:Double,lon:Double)  {

        Thread{
            val urlText = "https://api.weather.yandex.ru/v2/informers?lat=$lat&lon=$lon"
        val uri = URL(urlText)
        val urlConnection: HttpsURLConnection =
            (uri.openConnection() as HttpsURLConnection).apply {
                connectTimeout = 1000
                readTimeout = 1000
                addRequestProperty("X-Yandex-API-Key"," b9a5490d-2c9b-4311-a6bd-a7f0deaf20d9")
            }
            val headrs = urlConnection.headerFields
            val responsecode = urlConnection.responseCode
            val buffer = BufferedReader(InputStreamReader(urlConnection.inputStream))
            val weatherDTO:WeatherDTO = Gson().fromJson(buffer,WeatherDTO::class.java)
            Handler(Looper.getMainLooper()).post {
                onServerResponseListener.onResponse(weatherDTO)
            }

        }.start()



        
    }
}