package com.fintech.myapplication

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@HiltAndroidApp
class CarShowCaseApp : Application() {
    override fun onCreate() {
        super.onCreate()

        // Get UI mode and set
        val mode =
            if (isNight()) {
                AppCompatDelegate.MODE_NIGHT_YES
            } else {
                AppCompatDelegate.MODE_NIGHT_NO
            }

        AppCompatDelegate.setDefaultNightMode(mode)
    }
}
