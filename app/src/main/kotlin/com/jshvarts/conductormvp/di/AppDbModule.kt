package com.jshvarts.conductormvp.di

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
    fun provideNoteDao(context: Context): NoteDaoImpl = createNoteDao(context)

    @Singleton
    @Provides
    fun provideNoteModelMapper() = NoteModelMapperImpl()

    @Singleton
    @Provides
    fun provideNoteRepository(noteDao: NoteDaoImpl, mapper: NoteModelMapperImpl): NoteRepository = NoteRepositoryImpl(noteDao, mapper)
}