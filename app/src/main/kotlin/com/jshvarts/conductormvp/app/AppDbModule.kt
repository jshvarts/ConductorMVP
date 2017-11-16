package com.jshvarts.conductormvp.app

import android.arch.persistence.room.Room
import android.content.Context
import com.jshvarts.data.repository.NoteDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppDbModule {

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