package com.jshvarts.conductormvp.notedetail

import com.jshvarts.conductormvp.di.PerScreen
import com.jshvarts.notedomain.repository.NoteRepository
import com.jshvarts.notedomain.usecases.DeleteNoteUseCase
import com.jshvarts.notedomain.usecases.NoteDetailUseCase
import dagger.Module
import dagger.Provides

@Module
class NoteDetailModule {

    @PerScreen
    @Provides
    fun provideNoteDetailUseCase(noteRepository: NoteRepository) = NoteDetailUseCase(noteRepository)

    @PerScreen
    @Provides
    fun provideDeleteNoteUseCase(noteRepository: NoteRepository) = DeleteNoteUseCase(noteRepository)

    @PerScreen
    @Provides
    fun providePresenter(noteDetailUseCase: NoteDetailUseCase, deleteNoteUseCase: DeleteNoteUseCase) = NoteDetailPresenter(noteDetailUseCase, deleteNoteUseCase)
}