package com.example.kotlin_tasktwo.repository.DTO

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherDTO(
    val fact: Fact,
    val forecast: Forecast,
    val info: Info,
    val now: Int,
    val now_dt: String
):Parcelable

