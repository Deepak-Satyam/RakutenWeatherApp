package com.rakuten.weatherapp

import android.app.Application
import com.rakuten.weatherapp.api.networkModule
import com.rakuten.weatherapp.data.preferencesModule
import com.rakuten.weatherapp.data.repository.weatherDataRepoModule
import com.rakuten.weatherapp.ui.model.detailViewModel
import com.rakuten.weatherapp.ui.model.mainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(
                listOf(
                    networkModule,
                    preferencesModule,
                    weatherDataRepoModule,
                    mainViewModel,
                    detailViewModel
                )
            )
        }
    }
}
