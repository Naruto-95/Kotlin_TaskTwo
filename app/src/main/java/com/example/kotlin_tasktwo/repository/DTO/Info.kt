package com.example.kotlin_tasktwo.repository.DTO

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Info(

@SerializedName("lat")
val lat: Double,
@SerializedName("lon")
val lon: Double,
@SerializedName("url")
val url: String
):Parcelable