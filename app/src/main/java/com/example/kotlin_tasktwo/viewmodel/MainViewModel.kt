package com.example.kotlin_tasktwo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin_tasktwo.Repository.RepositoryImpl
import java.lang.Thread.sleep


class MainViewModel(private val liveDataToObserve: MutableLiveData<AppState> =MutableLiveData()) :
    ViewModel() {
    private val repositoryImpl: RepositoryImpl = RepositoryImpl()



    fun getLiveData() = liveDataToObserve
    fun getWeatherRussianFromLocalSource() = getDataFromLocalSource(isRussian = true)
    fun getWeatherWorldFromLocalSource() = getDataFromLocalSource(isRussian = false)



    private fun getDataFromLocalSource(isRussian: Boolean) {
        Thread {
            liveDataToObserve.postValue(AppState.Loading)
            sleep(2000)
            if (true) {
                val wea = if (!isRussian) repositoryImpl.getWeatherWorldFromLocalSource()
                else  repositoryImpl.getWeatherRussianFromLocalSource()
                liveDataToObserve.postValue(AppState.Success(wea))
            } else
                liveDataToObserve.postValue(AppState.Error(IllegalAccessException()))

        }.start()


    }
}