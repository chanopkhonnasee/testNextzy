package com.train.trainandroid2021.ui

import android.app.Application
import com.train.trainandroid2021.repository.Repository
import com.train.trainandroid2021.repository.RepositoryImpl
import com.train.trainandroid2021.repository.service.apiModule
import com.train.trainandroid2021.ui.main.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MainApplication: Application() {

    private val appModule = module {
        single<Repository>(createdAtStart = true) { RepositoryImpl(get()) }
        viewModel { MainViewModel(get()) }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(apiModule, appModule)
        }
    }
}