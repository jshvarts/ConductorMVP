package com.jshvarts.notedomain.usecases

import com.jshvarts.notedomain.model.Note
import com.jshvarts.notedomain.repository.NoteRepository
import io.reactivex.Completable

class AddNoteUseCase(private val repository: NoteRepository) {
    fun add(note: Note): Completable {
        return Completable.fromAction { note.validateForAdd() }
                .andThen(repository.insertOrUpdate(note))
    }
}