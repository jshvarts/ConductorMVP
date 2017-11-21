package com.jshvarts.conductormvp.app

import android.content.Context
import com.jshvarts.notedata.*
import com.jshvarts.notedomain.NoteRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppDbModule {

    @Singleton
    @Provides
    fun provideNoteDao(context: Context): RoomNoteDao = createNoteDao(context)

    @Singleton
    @Provides
    fun provideNoteModelMapper() = RoomNoteModelMapper()

    @Singleton
    @Provides
    fun provideNoteRepository(noteDao: RoomNoteDao, mapper: RoomNoteModelMapper): NoteRepository = RoomNoteRepository(noteDao, mapper)
}