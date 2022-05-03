package domain.room.room.repository_history

import com.example.kotlin_tasktwo.MyApp
import domain.convert.convertHistoryEntityToWeather
import domain.room.room.historyViewmodel.HistoryViewModel

interface RepositoryHistoryForALL {
    fun getWeatherHistoryForAll( callbak: HistoryViewModel.CallbakF)
}
