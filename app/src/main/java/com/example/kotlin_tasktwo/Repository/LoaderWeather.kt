package com.example.kotlin_tasktwo.Repository

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.kotlin_tasktwo.BuildConfig
import com.example.kotlin_tasktwo.Repository.DTO.WeatherDTO
import com.example.kotlin_tasktwo.utils.KAY_YANDEX
import com.example.kotlin_tasktwo.utils.KAY_YANDEX_API
import com.example.kotlin_tasktwo.utils.KAY_YANDEX_DOMEN
import com.example.kotlin_tasktwo.viewmodel.AppStateError
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class LoaderWeather (private val onServerResponseListener:OnServerResponse,
                     private val onErrorListener:OnErrorListener) {


    fun LoadWeather(lat: Double, lon: Double) {


        val urlText = "$KAY_YANDEX_DOMEN${KAY_YANDEX}lat=$lat&lon=$lon"
        val uri = URL(urlText)
        val urlConnection: HttpsURLConnection =
            (uri.openConnection() as HttpsURLConnection).apply {
                connectTimeout = 1000
                readTimeout = 1000
                addRequestProperty(KAY_YANDEX_API, BuildConfig.WEATHER_API_KEY)
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
                    onErrorListener.onError(AppStateError.ErrorSrv)
                    }
                    in clientSaid -> {
                        onErrorListener.onError(AppStateError.ErrorCl)
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
            onErrorListener.onError(AppStateError.ErrorCl)
        }  finally {
            urlConnection.disconnect()
        }
               }



    }






















        




