package com.jshvarts.conductormvp.app

import android.arch.persistence.room.Room
import android.content.Context
import com.jshvarts.data.repository.NoteRepository
import com.jshvarts.data.repository.room.RoomNoteDao
import com.jshvarts.data.repository.room.NoteDatabase
import com.jshvarts.data.repository.room.RoomNoteModelMapper
import com.jshvarts.data.repository.room.RoomNoteRepository
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

    @Singleton
    @Provides
    fun provideNoteModelMapper() = RoomNoteModelMapper()

    @Singleton
    @Provides
    fun provideNoteRepository(noteDao: RoomNoteDao, mapper: RoomNoteModelMapper): NoteRepository = RoomNoteRepository(noteDao, mapper)
}