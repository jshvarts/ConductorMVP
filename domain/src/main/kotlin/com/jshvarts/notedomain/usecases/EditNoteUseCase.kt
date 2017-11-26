package com.jshvarts.notedomain.usecases

import com.jshvarts.notedomain.model.Note
import com.jshvarts.notedomain.repository.NoteRepository
import io.reactivex.Completable

class EditNoteUseCase(private val repository: NoteRepository) {
    fun edit(note: Note): Completable {
        return Completable.fromAction { note.validateForEdit() }
                .andThen(repository.insertOrUpdate(note))
    }
}