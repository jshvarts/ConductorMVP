package com.jshvarts.notedomain.usecases

import com.jshvarts.notedomain.model.Note
import com.jshvarts.notedomain.repository.NoteRepository
import io.reactivex.Single

class NotesUseCase(private val repository: NoteRepository) {
    fun loadNotes(): Single<List<Note>> = repository.getAllNotes()
}