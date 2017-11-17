package com.jshvarts.conductormvp.data.room

import com.jshvarts.conductormvp.model.Note
import com.jshvarts.conductormvp.domain.NoteRepository

/**
 * Room implementation of {@link NoteRepository}.
 */
class RoomNoteRepository(private val noteDao: RoomNoteDao,
                         private val mapper: RoomNoteModelMapper) : NoteRepository {

    override fun add(note: Note) = noteDao.add(mapper.toEntity(note))

    override fun update(note: Note) = noteDao.update(mapper.toEntity(note))

    override fun delete(note: Note) = noteDao.delete(mapper.toEntity(note))

    override fun findNoteById(id: Long): Note = noteDao.findNoteById(id).run(mapper::fromEntity)

    override fun getAllNotes(): List<Note> = noteDao.getAllNotes().map(mapper::fromEntity)
}