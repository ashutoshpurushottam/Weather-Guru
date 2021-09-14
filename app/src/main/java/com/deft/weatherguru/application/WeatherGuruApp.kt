package com.deft.weatherguru.application

import android.app.Application
import android.util.Log
import com.deft.weatherguru.BuildConfig
import timber.log.Timber


class WeatherGuruApp: Application() {
    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}