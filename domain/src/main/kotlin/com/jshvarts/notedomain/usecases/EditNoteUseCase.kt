package com.jshvarts.notedomain.usecases

import com.jshvarts.notedomain.model.Note
import com.jshvarts.notedomain.model.NoteValidator
import com.jshvarts.notedomain.repository.NoteRepository
import io.reactivex.Completable

class EditNoteUseCase(private val repository: NoteRepository, private val validator: NoteValidator) {
    fun edit(note: Note): Completable {
        return Completable.fromAction { validator.validateForEdit(note) }
                .andThen(repository.insertOrUpdate(note))
    }
}