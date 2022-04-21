package com.example.kotlin_tasktwo.Repository

import android.os.Handler
import android.os.Looper
import com.example.kotlin_tasktwo.BuildConfig
import com.example.kotlin_tasktwo.Repository.DTO.WeatherDTO
import com.example.kotlin_tasktwo.utils.KEY_YANDEX
import com.example.kotlin_tasktwo.utils.KEY_YANDEX_API
import com.example.kotlin_tasktwo.utils.KEY_YANDEX_DOMEN
import com.example.kotlin_tasktwo.viewmodel.AppStateError
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.URL

class LoaderWeather (private val onServerResponseListener:OnServerResponse,
                     private val onErrorListener:OnErrorListener) {


    fun LoadWeather(lat: Double, lon: Double) {

//3 17
        val urlText = "$KEY_YANDEX_DOMEN${KEY_YANDEX}lat=$lat&lon=$lon"
        //val urlText = "http://212.86.114.27/v2/informers?lat=$lat&lon=$lon"
        val uri = URL(urlText)
        val urlConnection: HttpURLConnection =
            (uri.openConnection() as HttpURLConnection).apply {
                connectTimeout = 1000
                readTimeout = 1000
               addRequestProperty(KEY_YANDEX_API, BuildConfig.WEATHER_API_KEY)
            }

        try {
            Thread {
                val hareds = urlConnection.headerFields
                val responseCode = urlConnection.responseCode
                val serverSaid = 500..599
                val clientSaid = 400..499
                val responseOkey = 200..299
                when (responseCode) {
                    in serverSaid -> {
                    onErrorListener.onError(AppStateError.ErrorSrv(SocketTimeoutException()))
                    }
                    in clientSaid -> {
                        onErrorListener.onError(AppStateError.ErrorCl(IllegalAccessException()))
                    }
                    in responseOkey -> {
                        val buffer = BufferedReader(InputStreamReader(urlConnection.inputStream))
                       val weatherDTO: WeatherDTO = Gson().fromJson(buffer, WeatherDTO::class.java)
                     Handler(Looper.getMainLooper()).post {
                           onServerResponseListener.onResponse(weatherDTO)
                       }
                    }
                }
            }.start()

        } catch (e: Exception) {
            e.printStackTrace()
            onErrorListener.onError(AppStateError.ErrorCl(IllegalAccessException()))
        }  finally {
            urlConnection.disconnect()
        }
               }



    }






















        




