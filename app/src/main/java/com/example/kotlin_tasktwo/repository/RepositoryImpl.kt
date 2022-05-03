package com.example.kotlin_tasktwo.repository


class RepositoryImpl : Repository {
    override fun getWeatherFromRemoteSource()= Weather ()
//Добавил спсиок
    override fun getWeatherRussianFromLocalSource()=getRussianCities()
    override fun getWeatherWorldFromLocalSource()=getWorldCities()


}