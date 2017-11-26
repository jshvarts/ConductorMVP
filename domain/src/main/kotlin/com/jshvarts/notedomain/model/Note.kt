package com.jshvarts.notedomain.model

/**
 * Represents Data model
 */
data class Note(val id: Long = 0, val noteText: String = "") {

    fun validateForAdd() {
        require(validNoteText())
    }

    fun validateForEdit() {
        require(validId() && validNoteText())
    }

    private fun validId() = this.id > 0

    private fun validNoteText() = this.noteText.trim().length > 3
}