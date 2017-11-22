package com.jshvarts.notedomain

import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

/**
 * Repository interface to be implemented by Data layer.
 */
interface NoteRepository {

    fun add(note: Note): Completable

    fun update(note: Note): Completable

    fun delete(note: Note): Completable

    fun findNoteById(id: Long): Maybe<Note>

    fun getAllNotes(): Single<List<Note>>

    fun validateForAdd(note: Note) {
        require(note.noteText.trim().length > 3)
    }

    fun validateForUpdate(note: Note) {
        require(note.id > 0 && note.noteText.trim().length > 3)
    }

    fun validateForDelete(note: Note) {
        require(note.id > 0)
    }
}