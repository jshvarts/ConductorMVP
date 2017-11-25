package com.jshvarts.notedomain.usecases

import com.jshvarts.notedomain.model.Note
import com.jshvarts.notedomain.repository.NoteRepository
import io.reactivex.Maybe

class NoteDetailUseCase(private val repository: NoteRepository) {
    fun findNoteById(id: Long): Maybe<Note> = repository.findNoteById(id)
}