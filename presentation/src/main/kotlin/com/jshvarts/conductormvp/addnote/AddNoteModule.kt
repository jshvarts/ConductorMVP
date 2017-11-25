package com.jshvarts.conductormvp.addnote

import com.jshvarts.conductormvp.di.PerScreen
import com.jshvarts.notedomain.model.NoteValidator
import com.jshvarts.notedomain.repository.NoteRepository
import com.jshvarts.notedomain.usecases.AddNoteUseCase
import dagger.Module
import dagger.Provides

@Module
class AddNoteModule {

    @PerScreen
    @Provides
    fun provideAddNoteUseCase(noteRepository: NoteRepository, noteValidator: NoteValidator) = AddNoteUseCase(noteRepository, noteValidator)

    @PerScreen
    @Provides
    fun provideView() = AddNoteView()

    @PerScreen
    @Provides
    fun providePresenter(addNoteUseCase: AddNoteUseCase) = AddNotePresenter(addNoteUseCase)
}