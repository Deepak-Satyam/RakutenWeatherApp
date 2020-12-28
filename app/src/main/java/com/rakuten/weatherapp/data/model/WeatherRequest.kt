package com.rakuten.weatherapp.data.model

import com.google.gson.annotations.SerializedName

data class WeatherRequest(
    @SerializedName("type") val type: String,
    @SerializedName("query") val query: String
)