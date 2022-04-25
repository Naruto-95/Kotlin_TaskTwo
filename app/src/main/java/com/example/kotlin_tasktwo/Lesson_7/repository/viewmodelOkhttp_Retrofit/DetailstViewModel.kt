package com.example.kotlin_tasktwo.Lesson_7.repository.viewmodelOkhttp_Retrofit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin_tasktwo.Lesson_7.repository.DetailstRepository
import com.example.kotlin_tasktwo.Lesson_7.repository.DetailstRepositoryReteofitImpl
import com.example.kotlin_tasktwo.Repository.City
import com.example.kotlin_tasktwo.Repository.Weather
import com.example.kotlin_tasktwo.utils.ERROR_SERVER
import com.example.kotlin_tasktwo.viewmodel.AppStateError


class DetailstViewModel(
    private val liveData: MutableLiveData<DetailstState> = MutableLiveData(),
    //  private val repository: DetailstRepository = DetailstRepositoryOKHttpImpl(),
    private val repository: DetailstRepository = DetailstRepositoryReteofitImpl()
) : ViewModel() {

    fun getliveData() = liveData


/* fun getWeather(city: City) {
     liveData.postValue(DetailstState.Loading)
     repository.getWeatherDetailst(city) { weather ->
         liveData.postValue(DetailstState.Success(weather))


     }
 }*/


    fun getWeather(city:City) {
        repository.getWeatherDetailst(city, object : Callbak {
            override fun onResponse(weather: Weather) {
                liveData.postValue(DetailstState.Success(weather))
            }

            override fun onFail(detailstState: DetailstState) {
                liveData.postValue(DetailstState.Error(Throwable(ERROR_SERVER)))



            }

        }
        )
    }



    interface Callbak {
        fun onResponse(weather: Weather)
        fun onFail(detailstState: DetailstState)
    }


}


