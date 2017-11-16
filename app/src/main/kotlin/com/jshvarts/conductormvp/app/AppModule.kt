package com.jshvarts.conductormvp.app

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.jshvarts.data.repository.NoteDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideContext(application: Application) = application.applicationContext

    @Singleton
    @Provides
    fun provideNoteDatabase(context: Context): NoteDatabase =
            Room.databaseBuilder(context, NoteDatabase::class.java, "notesdb")
                    .allowMainThreadQueries()
                    .build()

    @Singleton
    @Provides
    fun provideNoteDao(database: NoteDatabase) = database.noteDao()
}