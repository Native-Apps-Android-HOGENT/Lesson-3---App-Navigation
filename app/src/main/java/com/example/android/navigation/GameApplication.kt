package com.example.android.navigation

import android.app.Application
import timber.log.Timber
import timber.log.Timber.DebugTree




class GameApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }
}