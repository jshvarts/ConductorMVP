package com.jshvarts.conductormvp.addnote

import com.jshvarts.conductormvp.app.PerScreen
import com.jshvarts.conductormvp.domain.NoteRepository
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