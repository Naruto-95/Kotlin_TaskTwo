package com.example.kotlin_tasktwo.Repository


class RepositoryImpl : Repository {
    override fun getWeatherFromRemoteSource(): Weather {
        return Weather()
    }
//Добавил спсиок
override fun getWeatherRussianFromLocalSource(): List<Weather> {
    return getRussianCities()
}
    override fun getWeatherWorldFromLocalSource(): List<Weather> {
        return getWorldCities()
    }


}