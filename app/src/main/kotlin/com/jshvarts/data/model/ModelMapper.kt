package com.jshvarts.data.model

/**
 * Maps between database entity and view item.
 */
class ModelMapper {
    fun fromEntity(from: NoteEntity) = Note(from.id, from.noteText)
    fun toEntity(from: Note) = NoteEntity(from.id, from.noteText, System.currentTimeMillis())
}