package com.jshvarts.conductormvp.app

import android.app.Application
import com.jshvarts.data.model.ModelMapper
import com.jshvarts.data.repository.NoteDao
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

    fun exposeNoteDao(): NoteDao
    fun exposeModelMapper(): ModelMapper
}