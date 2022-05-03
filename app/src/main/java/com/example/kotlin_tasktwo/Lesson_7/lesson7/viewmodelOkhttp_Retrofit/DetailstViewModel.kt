package com.example.kotlin_tasktwo.Lesson_7.lesson7.viewmodelOkhttp_Retrofit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin_tasktwo.Lesson_7.lesson7.DetailstRepository
import com.example.kotlin_tasktwo.Lesson_7.lesson7.DetailstRepositoryReteofitImpl
import com.example.kotlin_tasktwo.repository.City
import com.example.kotlin_tasktwo.repository.Weather
import com.example.kotlin_tasktwo.utils.ERROR_SERVER
import domain.room.room.repository_history.RepositoryHistoryAdd
import domain.room.room.repository_history.RepositoryHistoryRoomImp


class DetailstViewModel(
    private val liveData: MutableLiveData<DetailstState> = MutableLiveData(),
    //  private val repository: DetailstRepository = DetailstRepositoryOKHttpImpl(),
    private val repository: DetailstRepository = DetailstRepositoryReteofitImpl(),
            private val repositoryAdd: RepositoryHistoryAdd = RepositoryHistoryRoomImp()
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
                repositoryAdd.getAddWeather(weather)
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


