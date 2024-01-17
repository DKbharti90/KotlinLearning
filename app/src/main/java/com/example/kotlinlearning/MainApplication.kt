package com.example.kotlinlearning

import android.app.Application
import android.content.res.Configuration
import androidx.room.Room
import com.example.kotlinlearning.data.AppDatabase
import com.example.kotlinlearning.utils.AppConstant
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication:Application() {
    // Called when the application is starting, before any other application objects have been created.
    // Overriding this method is totally optional!
    override fun onCreate() {
        super.onCreate()
        // Required initialization logic here!

        //Data base Creation
    }

    // Called by the system when the device configuration changes while your component is running.
    // Overriding this method is totally optional!
    override fun onConfigurationChanged ( newConfig : Configuration ) {
        super.onConfigurationChanged(newConfig)
    }

    // This is called when the overall system is running low on memory,
    // and would like actively running processes to tighten their belts.
    // Overriding this method is totally optional!
    override fun onLowMemory() {
        super.onLowMemory()
    }
}