package com.jshvarts.notedata

import com.jshvarts.notedomain.model.Note
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class NoteModelMapperImplTest {

    private lateinit var testSubject: NoteModelMapperImpl

    companion object {
        val ID = 1L
        val NOTE_TEXT = "some text"
        val NOTE_TIMESTAMP = 2L
    }

    @Before
    fun setUp() {
        testSubject = NoteModelMapperImpl()
    }

    @Test
    fun fromEntity_convertsFromEntity() {
        // GIVEN
        val entity = NoteEntity(ID, NOTE_TEXT, NOTE_TIMESTAMP)

        // WHEN
        val result = testSubject.fromEntity(entity)

        // THEN
        assertTrue(result.id == ID)
        assertTrue(result.noteText == NOTE_TEXT)
    }

    @Test
    fun toEntity_convertsToEntity() {
        // GIVEN
        val note = Note(ID, NOTE_TEXT)

        // WHEN
        val result = testSubject.toEntity(note)

        // THEN
        assertTrue(result.id == ID)
        assertTrue(result.noteText == NOTE_TEXT)
        assertTrue(result.timestamp > 0)
    }
}