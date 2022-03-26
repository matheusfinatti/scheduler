package com.example.officescheduler

import android.app.Application
import com.example.core.injection.coreDataModule
import com.example.core.log.Log
import com.example.officescheduler.injection.appModule
import com.example.scheduler.injection.schedulerDataModule
import com.example.scheduler.injection.schedulerModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * Application override for sub-function initialization.
 */
class SchedulerApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Initializes logging
        Log.init()

        // Initializes dependency injection
        startKoin {
            androidLogger(level = if (BuildConfig.DEBUG) Level.DEBUG else Level.NONE)
            androidContext(this@SchedulerApplication)
            modules(listOf(appModule, coreDataModule, schedulerDataModule, schedulerModule))
        }

        Log.d("Application created")
    }
}
