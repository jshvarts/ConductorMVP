package com.jshvarts.data.repository.room

import com.jshvarts.data.model.Note
import com.jshvarts.data.repository.NoteRepository

/**
 * Room implementation of {@link RoomNoteRepository}.
 */
class RoomNoteRepository(private val noteDao: RoomNoteDao,
                         private val mapper: RoomNoteModelMapper) : NoteRepository {

    override fun add(note: Note) = noteDao.add(mapper.toEntity(note))

    override fun update(note: Note) = noteDao.update(mapper.toEntity(note))

    override fun delete(note: Note) = noteDao.delete(mapper.toEntity(note))

    override fun findNoteById(id: Long): Note = mapper.fromEntity(noteDao.findNoteById(id))

    override fun getAllNotes(): List<Note> = noteDao.getAllNotes().map(mapper::fromEntity)
}