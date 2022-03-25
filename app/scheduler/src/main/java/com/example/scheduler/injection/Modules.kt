package com.example.scheduler.injection

import com.example.scheduler.data.remote.SpacesApi
import com.example.scheduler.data.repository.SpacesRepositoryImpl
import com.example.scheduler.domain.repository.SpacesRepository
import kotlinx.coroutines.Dispatchers
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import retrofit2.Retrofit

val schedulerDataModule = module {

    // Retrofit instance for the scheduler API
    single<SpacesApi> {
        Retrofit.Builder()
            .client(get())
            .baseUrl(ENDPOINT)
            .build()
            .create(SpacesApi::class.java)
    }

    // Repository
    factory<SpacesRepository> {
        SpacesRepositoryImpl(Dispatchers.Default, get())
    }
}

private val loadModules by lazy {
    loadKoinModules(listOf(schedulerDataModule))
}

/**
 * Enables injecting.
 */
fun inject() = loadModules

private const val ENDPOINT = "http://127.0.0.1/"
