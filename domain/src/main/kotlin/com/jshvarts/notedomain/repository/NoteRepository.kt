package com.jshvarts.notedomain.repository

import com.jshvarts.notedomain.model.Note
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

/**
 * Repository interface to be implemented by Data layer.
 */
interface NoteRepository {

    fun insertOrUpdate(note: Note): Completable

    fun delete(note: Note): Completable

    fun findNoteById(id: Long): Maybe<Note>

    fun getAllNotes(): Single<List<Note>>
}