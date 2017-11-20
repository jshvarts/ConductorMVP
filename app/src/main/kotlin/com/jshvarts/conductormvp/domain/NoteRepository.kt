package com.jshvarts.conductormvp.domain

import com.jshvarts.conductormvp.domain.model.Note
import io.reactivex.Maybe
import io.reactivex.Single

/**
 * Repository interface that all DataStore implementations should implement.
 */
interface NoteRepository {
    fun add(note: Note)

    fun update(note: Note)

    fun delete(note: Note)

    fun findNoteById(id: Long): Maybe<Note>

    fun getAllNotes(): Single<List<Note>>
}