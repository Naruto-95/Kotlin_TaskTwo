package domain.room.room.repository_history

import domain.room.room.historyViewmodel.HistoryViewModel

interface RepositoryHistoryForALL {
    fun getWeatherHistoryForAll( callbak: HistoryViewModel.CallbakF)
}
