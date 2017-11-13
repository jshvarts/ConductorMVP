package com.jshvarts.conductormvp.app

import android.app.Application
import com.jshvarts.conductormvp.BuildConfig
import timber.log.Timber

class App : Application() {
    companion object {
        lateinit var component: AppComponent
            private set
    }

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder()
                .application(this)
                .build()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}