package com.jshvarts.conductormvp

import android.app.Application
import com.jshvarts.conductormvp.di.AppComponent
import com.jshvarts.conductormvp.di.DaggerAppComponent
import timber.log.Timber

class NotesApp : Application() {
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