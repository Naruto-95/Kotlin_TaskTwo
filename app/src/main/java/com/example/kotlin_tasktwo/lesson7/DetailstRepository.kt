package com.example.kotlin_tasktwo.lesson7

import com.example.kotlin_tasktwo.lesson7.viewmodelOkhttp_Retrofit.DetailstViewModel
import com.example.kotlin_tasktwo.repository.City

interface DetailstRepository {
    fun getWeatherDetailst(city: City,callbak: DetailstViewModel.Callbak)
}