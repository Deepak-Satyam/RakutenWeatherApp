package com.rakuten.weatherapp.api

import com.rakuten.weatherapp.BuildConfig.WEATHER_TOKEN
import com.rakuten.weatherapp.data.model.Weather
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    @GET("/current?access_key=${WEATHER_TOKEN}")
    suspend fun queryCurrentWeather(@Query("query") key: String): Weather
}