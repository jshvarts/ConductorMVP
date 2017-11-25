package com.jshvarts.notedata

import com.jshvarts.notedomain.model.Note
import com.jshvarts.notedomain.repository.NoteRepository
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

/**
 * Room implementation of {@link NoteRepository}.
 */
class NoteRepositoryImpl(private val noteDao: NoteDaoImpl,
                         private val mapper: NoteModelMapperImpl) : NoteRepository {

    override fun add(note: Note): Completable {
        return Completable.fromAction {
            validateForAdd(note)
            noteDao.add(mapper.toEntity(note))
        }
    }

    override fun update(note: Note): Completable {
        return Completable.fromAction {
            validateForUpdate(note)
            noteDao.update(mapper.toEntity(note))
        }
    }

    override fun delete(note: Note): Completable {
        return Completable.fromAction {
            validateForDelete(note)
            noteDao.delete(mapper.toEntity(note))
        }
    }

    override fun findNoteById(id: Long): Maybe<Note> {
        return noteDao.findNoteById(id)
                .map { mapper.fromEntity(it) }
    }

    override fun getAllNotes(): Single<List<Note>> {
        return noteDao.getAllNotes()
                .map { it.map(mapper::fromEntity) }
    }
}