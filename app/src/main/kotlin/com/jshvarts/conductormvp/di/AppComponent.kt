package com.jshvarts.conductormvp.di

import android.app.Application
import com.jshvarts.notedomain.NoteRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun exposeNoteRepository(): NoteRepository
}