package com.example.kotlin_tasktwo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin_tasktwo.view.main.RepositoryImpl
import java.lang.Thread.sleep
import java.util.*

class MainViewModel(private val liveDataToObserve: MutableLiveData<AppState> =MutableLiveData()) :
    ViewModel() {
    private val repositoryImpl: RepositoryImpl = RepositoryImpl()



   /* fun getData(): LiveData<Any>{
        getDataFromLocalSource()
        return liveDataToObserve

    }*/


    fun getLiveData() = liveDataToObserve
    fun getWeather() = getDataFromLocalSource()
    //fun getWeatherFromRemoteSource() = getDataFromLocalSource()
    //fun getWeatherFromLocalSource() = getDataFromLocalSource()




    private fun getDataFromLocalSource(){
        liveDataToObserve.postValue(AppState.Loading)
        Thread{
            sleep(2000)
            val random = Random(15).nextInt()
            if (random > 0)
                liveDataToObserve.postValue(AppState.Error(IllegalStateException()))
            else
                liveDataToObserve.postValue(AppState.Success(repositoryImpl.  getWeatherFromRemoteSource()))
        }.start()

    }


}