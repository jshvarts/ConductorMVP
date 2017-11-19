package com.jshvarts.conductormvp.addnote

import com.jshvarts.conductormvp.app.PerScreen
import com.jshvarts.conductormvp.domain.NoteRepository
import dagger.Module
import dagger.Provides

@Module
class AddNoteModule {

    @PerScreen
    @Provides
    fun provideView() = AddNoteView()

    @PerScreen
    @Provides
    fun providePresenter(noteRepository: NoteRepository) = AddNotePresenter(noteRepository)
}