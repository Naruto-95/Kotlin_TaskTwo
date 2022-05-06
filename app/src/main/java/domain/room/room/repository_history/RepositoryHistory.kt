package domain.room.room.repository_history

import com.example.kotlin_tasktwo.lesson7.viewmodelOkhttp_Retrofit.DetailstViewModel
import com.example.kotlin_tasktwo.repository.City

interface RepositoryHistory {
    fun getWeatherHistory(city: City, callbak: DetailstViewModel.Callbak)
}
