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
}