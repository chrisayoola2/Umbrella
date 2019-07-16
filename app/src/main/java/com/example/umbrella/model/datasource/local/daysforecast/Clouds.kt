package com.example.umbrella.model.datasource.local.daysforecast


import com.google.gson.annotations.SerializedName

data class Clouds(
    @SerializedName("all")
    val all: Int
)