package com.jshvarts.data.repository

import com.jshvarts.data.model.NoteEntity
import io.reactivex.Flowable
import io.reactivex.Completable
import io.reactivex.Single

interface NoteRepository {
    fun add(note: NoteEntity): Single<NoteEntity>
    fun update(note: NoteEntity): Completable
    fun delete(note: NoteEntity): Completable
    fun findNoteById(): Single<NoteEntity>
    fun getAllNotes(): Flowable<List<NoteEntity>>
}