package com.jshvarts.conductormvp.notedetail

import com.jshvarts.conductormvp.app.PerScreen
import com.jshvarts.notedomain.NoteRepository
import dagger.Module
import dagger.Provides

@Module
class NoteDetailModule {

    @PerScreen
    @Provides
    fun provideView() = NoteDetailView()

    @PerScreen
    @Provides
    fun providePresenter(noteRepository: NoteRepository) = NoteDetailPresenter(noteRepository)
}