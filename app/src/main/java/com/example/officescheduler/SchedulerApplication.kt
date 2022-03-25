package com.example.officescheduler

import android.app.Application
import com.example.core.log.Log

class SchedulerApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Initializes logging
        Log.init()

        Log.d("Application created")
    }
}
