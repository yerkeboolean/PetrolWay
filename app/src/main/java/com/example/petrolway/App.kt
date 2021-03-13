package com.example.petrolway

import android.app.Application
import android.content.Context
import com.example.core.presentation.di.coreModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {

    companion object {
        lateinit var appContext: Context
    }


    override fun onCreate() {
        super.onCreate()
        setupTimber()
        Timber.i("onCreate")

        appContext = applicationContext

        startKoin {
            androidContext(this@App)
            modules(coreModules)
        }

    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }


}