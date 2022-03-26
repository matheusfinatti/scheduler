package com.example.officescheduler.injection

import com.example.navigation.NavigationManager
import org.koin.dsl.module

val appModule = module {

    // Navigation
    single {
        NavigationManager()
    }
}
