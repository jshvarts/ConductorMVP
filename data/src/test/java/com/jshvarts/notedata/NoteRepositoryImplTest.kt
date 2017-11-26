package com.jshvarts.notedata

import com.jshvarts.notedomain.model.Note
import com.nhaarman.mockito_kotlin.*
import io.reactivex.Maybe
import io.reactivex.Single
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.hamcrest.Matchers.`is` as is_

class NoteRepositoryImplTest {

    private val noteDao: NoteDaoImpl = mock()

    private val noteMapper: NoteModelMapperImpl = mock()

    private val testSubject: NoteRepositoryImpl = NoteRepositoryImpl(noteDao, noteMapper)

    @Test
    fun insertOrUpdate_emitsOnSuccess() {
        // GIVEN
        val note = note()
        val noteEntity = given_entityFromNote(note)

        // WHEN
        val testObserver = testSubject.insertOrUpdate(note).test()

        // THEN
        testObserver.assertComplete()

        argumentCaptor<NoteEntity>().apply {
            verify(noteDao).insertOrUpdate(capture())
            assertThat(firstValue.id, is_(noteEntity.id))
            assertThat(firstValue.noteText, is_(noteEntity.noteText))
        }
    }

    @Test
    fun delete_emitsOnSuccess() {
        // GIVEN
        val note = note()
        val noteEntity = given_entityFromNote(note)

        // WHEN
        val testObserver = testSubject.delete(note).test()

        // THEN
        testObserver.assertComplete()

        argumentCaptor<NoteEntity>().apply {
            verify(noteDao).delete(capture())
            assertThat(firstValue.id, is_(noteEntity.id))
            assertThat(firstValue.noteText, is_(noteEntity.noteText))
        }
    }

    @Test
    fun findNoteById_givenItemFound_emitsNote() {
        // GIVEN
        val noteEntity = noteEntity()
        val note = given_noteFromEntity(noteEntity)
        whenever(noteDao.findNoteById(any())).thenReturn(Maybe.just(noteEntity))

        // WHEN
        val testObserver = testSubject.findNoteById(note.id).test()

        // THEN
        testObserver.assertResult(note)

        argumentCaptor<Long>().apply {
            verify(noteDao).findNoteById(capture())
            assertThat(firstValue, is_(note.id))
        }

        verify(noteMapper).fromEntity(noteEntity)
    }

    @Test
    fun findNoteById_givenItemNotFound_emitsEmpty() {
        // GIVEN
        whenever(noteDao.findNoteById(any())).thenReturn(Maybe.empty())

        // WHEN
        val testObserver = testSubject.findNoteById(1).test()

        // THEN
        testObserver.assertComplete()
        testObserver.assertNoValues()
    }

    @Test
    fun getAllNotes_emitsValues() {
        // GIVEN
        val entity = noteEntity()
        val noteEntities = listOf(entity)
        val note = given_noteFromEntity(entity)
        whenever(noteDao.getAllNotes()).thenReturn(Single.just(noteEntities))

        // WHEN
        val testObserver = testSubject.getAllNotes().test()

        // THEN
        verify(noteMapper).fromEntity(entity)
        testObserver.assertResult(listOf(note))
        testObserver.assertComplete()
    }

    private fun note() = Note(1, "test note")

    private fun noteEntity() = NoteEntity(2, "test entity", 10L)

    private fun given_entityFromNote(note: Note): NoteEntity {
        val noteEntity: NoteEntity = mock()
        whenever(noteMapper.toEntity(note)).thenReturn(noteEntity)
        whenever(noteEntity.id).thenReturn(note.id)
        whenever(noteEntity.noteText).thenReturn(note.noteText)
        return noteEntity
    }

    private fun given_noteFromEntity(noteEntity: NoteEntity): Note {
        val note: Note = mock()
        whenever(noteMapper.fromEntity(noteEntity)).thenReturn(note)
        whenever(note.id).thenReturn(noteEntity.id)
        whenever(note.noteText).thenReturn(noteEntity.noteText)
        return note
    }
}