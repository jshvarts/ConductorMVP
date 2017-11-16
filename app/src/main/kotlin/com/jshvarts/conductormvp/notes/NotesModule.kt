package com.jshvarts.conductormvp.notes

import com.jshvarts.conductormvp.app.PerScreen
import com.jshvarts.data.repository.NoteDao
import dagger.Module
import dagger.Provides

@Module
class NotesModule {

    @PerScreen
    @Provides
    fun provideView() = NotesView()

    @PerScreen
    @Provides
    fun providePresenter(noteDao: NoteDao) = NotesPresenter(noteDao)
}