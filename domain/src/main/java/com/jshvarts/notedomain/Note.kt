package com.jshvarts.notedomain

/**
 * Data holder used by View layer.
 */
data class Note(
        val id: Long = 0,
        val noteText: String = "") {
    fun isValid(): Boolean = noteText.trim().length > 3
}