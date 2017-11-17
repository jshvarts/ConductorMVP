package com.jshvarts.conductormvp.app

import android.arch.persistence.room.Room
import android.content.Context
import com.jshvarts.conductormvp.domain.NoteRepository
import com.jshvarts.conductormvp.data.room.RoomNoteDao
import com.jshvarts.conductormvp.data.room.NoteDatabase
import com.jshvarts.conductormvp.data.room.RoomNoteModelMapper
import com.jshvarts.conductormvp.data.room.RoomNoteRepository
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