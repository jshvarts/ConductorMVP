package com.jshvarts.conductormvp.notes

import com.jshvarts.conductormvp.app.PerScreen
import com.jshvarts.notedomain.NoteRepository
import dagger.Module
import dagger.Provides

@Module
class NotesModule {

    @PerScreen
    @Provides
    fun provideView() = NotesView()

    @PerScreen
    @Provides
    fun providePresenter(noteRepository: NoteRepository) = NotesPresenter(noteRepository)
}