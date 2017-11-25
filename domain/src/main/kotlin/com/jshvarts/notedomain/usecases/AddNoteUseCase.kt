package com.jshvarts.notedomain.usecases

import com.jshvarts.notedomain.model.Note
import com.jshvarts.notedomain.model.NoteValidator
import com.jshvarts.notedomain.repository.NoteRepository
import io.reactivex.Completable

class AddNoteUseCase(private val repository: NoteRepository, private val validator: NoteValidator) {
    fun add(note: Note): Completable {
        return Completable.fromAction { validator.validateForAdd(note) }
                .andThen(repository.insertOrUpdate(note))
    }
}