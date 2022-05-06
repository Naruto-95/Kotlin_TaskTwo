package domain.room.room.repository_history

import com.example.kotlin_tasktwo.lesson7.viewmodelOkhttp_Retrofit.DetailstViewModel
import com.example.kotlin_tasktwo.MyApp
import com.example.kotlin_tasktwo.repository.City
import com.example.kotlin_tasktwo.repository.Weather
import domain.convert.convertHistoryEntityToWeather
import domain.convert.convertWeatherToEntity
import domain.room.room.historyViewmodel.HistoryViewModel

class RepositoryHistoryRoomImp : RepositoryHistory, RepositoryHistoryForALL,RepositoryHistoryAdd {
    override fun getWeatherHistory(city: City, callbak: DetailstViewModel.Callbak) {
        var list = convertHistoryEntityToWeather(MyApp.getHistoryDao().getHistoriForCity(city.name))
        callbak.onResponse(list.last())
    }

    override fun getWeatherHistoryForAll(callbak: HistoryViewModel.CallbakF) {

            callbak.onResponse(convertHistoryEntityToWeather(MyApp.getHistoryDao().getAll()))


    }


    override fun getAddWeather(weather: Weather) {
        MyApp.getHistoryDao().insert(convertWeatherToEntity(weather))
    }

}
