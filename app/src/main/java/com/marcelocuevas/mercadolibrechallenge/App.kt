package com.marcelocuevas.mercadolibrechallenge

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber
import timber.log.Timber.DebugTree


class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin()
        startTimber()
    }

    private fun startKoin() {
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(listOf(dataModule, useCaseModule, appModule))
        }
    }

    private fun startTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }
}