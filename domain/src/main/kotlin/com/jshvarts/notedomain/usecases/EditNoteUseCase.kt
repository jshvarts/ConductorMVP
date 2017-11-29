package com.jshvarts.notedomain.usecases

import com.jshvarts.notedomain.model.Note
import com.jshvarts.notedomain.repository.NoteRepository
import io.reactivex.Completable

class EditNoteUseCase(private val repository: NoteRepository) {
    fun edit(note: Note): Completable = validate(note).andThen(repository.insertOrUpdate(note))

    private fun validate(note: Note): Completable {
        return if (!note.isValidForEdit()) {
            Completable.error(IllegalArgumentException("note failed validation before edit"))
        } else {
            Completable.complete()
        }
    }
}