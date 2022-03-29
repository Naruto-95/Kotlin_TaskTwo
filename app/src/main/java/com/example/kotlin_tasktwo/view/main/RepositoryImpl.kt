package com.example.kotlin_tasktwo.view.main


class RepositoryImpl : Repository {
    override fun getWeatherFromRemoteSource(): Weather {
        return Weather()
    }

    override fun getWeatherFromLocalSource(): Weather {
        return Weather()
    }

}