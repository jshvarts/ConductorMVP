package com.jshvarts.notedomain.usecases

import com.jshvarts.notedomain.model.Note
import com.jshvarts.notedomain.repository.NoteRepository
import io.reactivex.Completable

class DeleteNoteUseCase(private val repository: NoteRepository) {
    fun delete(note: Note): Completable = repository.delete(note)
}