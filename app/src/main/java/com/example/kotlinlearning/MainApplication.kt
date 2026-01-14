package com.example.kotlinlearning

import android.app.Application
import android.content.res.Configuration
import androidx.room.Room
import com.example.kotlinlearning.data.AppDatabase
import com.example.kotlinlearning.utils.AppConstant
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication:Application() {
    override fun onCreate() {
        super.onCreate()
    }
    override fun onConfigurationChanged ( newConfig : Configuration ) {
        super.onConfigurationChanged(newConfig)
    }
    override fun onLowMemory() {
        super.onLowMemory()
    }
}