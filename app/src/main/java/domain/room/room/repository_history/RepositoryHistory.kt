package domain.room.room.repository_history

import com.example.kotlin_tasktwo.Lesson_7.repository.viewmodelOkhttp_Retrofit.DetailstViewModel
import com.example.kotlin_tasktwo.Repository.City
import domain.room.room.historyViewmodel.HistoryViewModel

interface RepositoryHistory {
    fun getWeatherHistory(city: City, callbak: DetailstViewModel.Callbak)
}
