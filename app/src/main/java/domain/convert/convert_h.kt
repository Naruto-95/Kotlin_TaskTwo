package domain.convert

import com.example.kotlin_tasktwo.Repository.City
import com.example.kotlin_tasktwo.Repository.Weather
import domain.room.room.HistoryMyApplication



    fun convertHistoryEntityToWeather(entityList: List<HistoryMyApplication>):

            List<Weather> {
        return entityList.map {
            Weather(City(it.city, 0.0, 0.0), it.temperature, it.feelsLike, it.icon)
        }
    }

    fun convertWeatherToEntity(weather: Weather): HistoryMyApplication {
        return HistoryMyApplication(
            0, weather.city.name, weather.temperature, weather.feelsLike,
            weather.icon
        )

    }
