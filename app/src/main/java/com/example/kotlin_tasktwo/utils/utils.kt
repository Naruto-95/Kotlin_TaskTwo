package com.example.kotlin_tasktwo.utils

import com.example.kotlin_tasktwo.repository.DTO.Fact
import com.example.kotlin_tasktwo.repository.DTO.WeatherDTO
import com.example.kotlin_tasktwo.repository.Weather
import com.example.kotlin_tasktwo.repository.getDefaultCity

//приветсвие сервиса в логах(серверы)
const val KEY_SERVICE = "kay_service"
const val KEY_RECEIVER = "kay_receiver"

//ошибки
const val ERROR_SERVER = "ошибка сервера"
const val REQUEST_ERROR = "Ошибка запроса на сервер"


// Яндекс домены и тд
const val KEY_YANDEX = "v2/informers?"
const val KEY_YANDEX_DOMEN = "https://api.weather.yandex.ru/"
const val KEY_YANDEX_API = "X-Yandex-API-Key"

//Проект
const val KEY_BUN_LAT = "lat"
const val KEY_BUN_LON = "lon"
const val KEY_WEATHER = "weather"
const val KEY_WEATHER_WAVE = "weather_wave"

const val KEY_SETTINGS = "settings"
const val  KEY_RUSSIAN_P = "isRussianPreferences "
const val  KEY_APP_RUSSIAN_P = "FailAppIsRussianPreferences "

//слипы сервеса
const val SLEEP_SERVICE = 1000L

//Wave
const val WAVE_MY_ACTION = "MyAction"


//Переводчик клиента на сервер и на оборот
fun convertDtoToModel(weatherDTO: WeatherDTO): Weather {
    val fact: Fact = weatherDTO.fact!!
    return (Weather(getDefaultCity(), fact.temperature!!, fact.feelsLike!!, fact.icon))
}