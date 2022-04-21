package com.example.kotlin_tasktwo.Details

import android.app.IntentService
import android.content.Intent
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.kotlin_tasktwo.BuildConfig
import com.example.kotlin_tasktwo.Repository.DTO.WeatherDTO
import com.example.kotlin_tasktwo.utils.*
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class DetalisService (val name: String = ""): IntentService(name) {
    override fun onHandleIntent(intent: Intent?) {
        Log.d("@@@","DetalisService")
        intent?.let {
            val lat= it.getDoubleExtra(KEY_BUN_LAT,0.0)
            val lon = it.getDoubleExtra(KEY_BUN_LON,0.0)
            Log.d("@@@","DetalisService $lat $lon ")

            val urlText = "$KEY_YANDEX_DOMEN${KEY_YANDEX}lat=$lat&lon=$lon"
            val uri = URL(urlText)
            val urlConnection: HttpsURLConnection =
                (uri.openConnection() as HttpsURLConnection).apply {
                    connectTimeout = 1000
                    readTimeout = 1000
                    addRequestProperty(KEY_YANDEX_API, BuildConfig.WEATHER_API_KEY)
                }



                    val hareds = urlConnection.headerFields
                    val responseCode = urlConnection.responseCode
            val buffer = BufferedReader(InputStreamReader(urlConnection.inputStream))
            val weatherDTO: WeatherDTO = Gson().fromJson(buffer, WeatherDTO::class.java)
            val message = Intent(KEY_WEATHER_WAVE)
            message.putExtra(KEY_WEATHER,weatherDTO)
            //sendBroadcast(message)
            LocalBroadcastManager.getInstance(this).sendBroadcast(message)







        }




    }

}
