package com.jshvarts.conductormvp.app

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = arrayOf(AppDbModule::class))
class AppModule {

    @Singleton
    @Provides
    fun provideContext(application: Application) = application.applicationContext
}