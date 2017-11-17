package com.jshvarts.data.repository.room

import com.jshvarts.data.model.Note
import com.jshvarts.data.repository.NoteModelMapper

/**
 * Maps between Room database entity and view item.
 */
class RoomNoteModelMapper : NoteModelMapper<NoteEntity, Note> {
    override fun fromEntity(from: NoteEntity) = Note(from.id, from.noteText)
    override fun toEntity(from: Note) = NoteEntity(from.id, from.noteText, System.currentTimeMillis())
}