package com.jshvarts.conductormvp.editnote

import com.jshvarts.conductormvp.di.PerScreen
import com.jshvarts.notedomain.NoteRepository
import dagger.Module
import dagger.Provides

@Module
class EditNoteModule {

    @PerScreen
    @Provides
    fun provideView() = EditNoteView()

    @PerScreen
    @Provides
    fun providePresenter(noteRepository: NoteRepository) = EditNotePresenter(noteRepository)
}