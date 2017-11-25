package com.jshvarts.notedomain.model

class NoteValidator {
    fun validateForAdd(note: Note) {
        require(note.noteText.trim().length > 3)
    }

    fun validateForEdit(note: Note) {
        require(note.id > 0 && note.noteText.trim().length > 3)
    }
}