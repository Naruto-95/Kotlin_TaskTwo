package domain.room.room.historyViewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin_tasktwo.Repository.Weather
import com.example.kotlin_tasktwo.viewmodel.AppState
import domain.room.room.repository_history.RepositoryHistoryRoomImp

class HistoryViewModel (private val liveData: MutableLiveData<AppState> = MutableLiveData()) :
    ViewModel() {
    private val repository: RepositoryHistoryRoomImp = RepositoryHistoryRoomImp()




    fun getLiveData() = liveData

    fun getAll(){
        repository.getWeatherHistoryForAll(object :CallbakF{
            override fun onResponse(listweather: List<Weather>) {
                liveData.postValue(AppState.Success(listweather))
            }

        })


    }


    interface CallbakF {
        fun onResponse(weather: List<Weather>)

    }



}