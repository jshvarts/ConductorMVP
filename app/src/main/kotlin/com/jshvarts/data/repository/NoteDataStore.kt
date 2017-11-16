package com.jshvarts.data.repository

import com.jshvarts.data.model.NoteEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

class NoteDataStore(private val noteDao: NoteDao) : NoteRepository {
    override fun add(noteEntity: NoteEntity): Single<NoteEntity> {
        TODO("not implemented")
    }

    override fun update(note: NoteEntity): Completable {
        TODO("not implemented")
    }

    override fun delete(note: NoteEntity): Completable {
        TODO("not implemented")
    }

    override fun findNoteById(): Single<NoteEntity> {
        TODO("not implemented")
    }

    override fun getAllNotes(): Flowable<List<NoteEntity>> {
        TODO("not implemented")
    }
}