package com.example.kotlin_tasktwo.Lesson_7.lesson7

import com.example.kotlin_tasktwo.Lesson_7.lesson7.viewmodelOkhttp_Retrofit.DetailstViewModel
import com.example.kotlin_tasktwo.repository.City

interface DetailstRepository {
    fun getWeatherDetailst(city: City,callbak: DetailstViewModel.Callbak)
}