package com.jshvarts.conductormvp.notes

import com.jshvarts.conductormvp.di.PerScreen
import com.jshvarts.notedomain.repository.NoteRepository
import com.jshvarts.notedomain.usecases.NotesUseCase
import dagger.Module
import dagger.Provides

@Module
class NotesModule {

    @PerScreen
    @Provides
    fun provideNotesUseCase(noteRepository: NoteRepository) = NotesUseCase(noteRepository)

    @PerScreen
    @Provides
    fun providePresenter(notesUseCase: NotesUseCase) = NotesPresenter(notesUseCase)
}