package com.jshvarts.conductormvp.addnote

import com.jshvarts.conductormvp.di.PerScreen
import com.jshvarts.notedomain.NoteRepository
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