package com.jshvarts.conductormvp.data.room

import com.jshvarts.conductormvp.model.Note
import com.jshvarts.conductormvp.data.NoteModelMapper

/**
 * Maps between Room database entity and model.
 */
class RoomNoteModelMapper : NoteModelMapper<NoteEntity, Note> {
    override fun fromEntity(from: NoteEntity) = Note(from.id, from.noteText)
    override fun toEntity(from: Note) = NoteEntity(from.id, from.noteText, System.currentTimeMillis())
}