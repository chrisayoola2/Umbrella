package com.example.umbrella.model.datasource.local.daysforecast


import com.google.gson.annotations.SerializedName

data class City(
    @SerializedName("coord")
    val coord: Coord,
    @SerializedName("country")
    val country: String,
    @SerializedName("name")
    val name: String
)