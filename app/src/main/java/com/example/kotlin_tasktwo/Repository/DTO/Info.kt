package com.example.kotlin_tasktwo.Repository.DTO

import com.google.gson.annotations.SerializedName

data class Info(

@SerializedName("lat")
val lat: Double,
@SerializedName("lon")
val lon: Double,
@SerializedName("url")
val url: String
)