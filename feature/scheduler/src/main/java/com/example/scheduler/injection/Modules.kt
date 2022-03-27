package com.example.scheduler.injection

import androidx.lifecycle.SavedStateHandle
import com.example.core.network.LocalWebServer
import com.example.scheduler.data.remote.SpacesApi
import com.example.scheduler.data.repository.SpacesRepositoryImpl
import com.example.scheduler.domain.repository.SpacesRepository
import com.example.scheduler.domain.usecases.GetAllSpacesEntriesUseCase
import com.example.scheduler.presentation.SchedulerViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Defines the data module for the scheduler feature.
 */
val schedulerDataModule = module {

    // Retrofit instance for the scheduler API
    single<SpacesApi> {
        Retrofit.Builder()
            .client(get())
            .baseUrl(get<LocalWebServer>().url)
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .build()
            .create(SpacesApi::class.java)
    }

    // Repository
    factory<SpacesRepository> {
        SpacesRepositoryImpl(Dispatchers.Default, get())
    }
}

val schedulerModule = module {

    // Use cases
    factory { GetAllSpacesEntriesUseCase(get()) }

    // View Model
    viewModel {
        SchedulerViewModel(
            navigationManager = get(),
            getAllSpacesEntriesUseCase = get()
        )
    }
}
