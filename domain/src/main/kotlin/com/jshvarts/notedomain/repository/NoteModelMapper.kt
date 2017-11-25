package com.jshvarts.notedomain.repository

/**
 * Data mapper used to map database entities to model classes and vice versa.
 */
interface NoteModelMapper<E, M> {
    fun fromEntity(from: E): M
    fun toEntity(from: M): E
}