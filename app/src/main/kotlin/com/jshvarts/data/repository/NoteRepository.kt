package com.jshvarts.data.repository

import com.jshvarts.data.model.Note

/**
 * Repository interface that all DataStore implementations should implement.
 */
interface NoteRepository {
    fun add(note: Note)

    fun update(note: Note)

    fun delete(note: Note)

    fun findNoteById(id: Long): Note

    fun getAllNotes(): List<Note>
}