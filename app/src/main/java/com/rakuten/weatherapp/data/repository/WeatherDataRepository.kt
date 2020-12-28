package com.rakuten.weatherapp.data.repository

import com.rakuten.weatherapp.api.WeatherAPI
import org.koin.dsl.module

val weatherDataRepoModule = module {
    factory { WeatherDataRepository(get()) }
}

class WeatherDataRepository(private val weatherAPI: WeatherAPI) {
    suspend fun queryWeather(key: String) = weatherAPI.queryCurrentWeather(key)
}