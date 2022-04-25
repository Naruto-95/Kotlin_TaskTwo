package com.example.kotlin_tasktwo.Lesson_7.repository

import com.example.kotlin_tasktwo.Lesson_7.repository.viewmodelOkhttp_Retrofit.DetailstViewModel
import com.example.kotlin_tasktwo.Repository.City

interface DetailstRepository {
    fun getWeatherDetailst(city: City,callbak: DetailstViewModel.Callbak)
}