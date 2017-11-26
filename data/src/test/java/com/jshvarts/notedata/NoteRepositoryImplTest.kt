package com.jshvarts.notedata

import com.jshvarts.notedomain.model.Note
import com.nhaarman.mockito_kotlin.capture
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Test
import org.mockito.*

class NoteRepositoryImplTest {

    @InjectMocks
    @Spy
    private lateinit var testSubject: NoteRepositoryImpl

    @Mock
    private lateinit var noteDao: NoteDaoImpl

    @Mock
    private lateinit var noteMapper: NoteModelMapperImpl

    @Captor
    private lateinit var noteArgumentCaptor: ArgumentCaptor<Note>

    @Mock
    private lateinit var noteEntity: NoteEntity

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun insertOrUpdate_emitsOnSuccess() {
        // GIVEN
        val note = Note(0, "test")
        whenever(noteMapper.toEntity(note)).thenReturn(noteEntity)

        // WHEN
        val testObserver = testSubject.insertOrUpdate(note).test()

        // THEN
        testObserver.assertComplete()

        verify(noteDao).insertOrUpdate(noteEntity)
        verify(testSubject).insertOrUpdate(capture(noteArgumentCaptor))
        assertThat(noteArgumentCaptor.value.noteText, Matchers.`is`(note.noteText))
    }

    @Test
    fun insertOrUpdate_emitsOnError() {
        // GIVEN
        val exception = Exception()
        val note = Note(0, "test")
        whenever(testSubject.insertOrUpdate(note)).thenReturn(Completable.error(exception))

        // WHEN
        val testObserver = testSubject.insertOrUpdate(note).test()

        // THEN
        testObserver.assertError(exception)
    }

    @Test
    fun delete_emitsOnSuccess() {
        // GIVEN
        val note = Note(0, "test")
        whenever(noteMapper.toEntity(note)).thenReturn(noteEntity)

        // WHEN
        val testObserver = testSubject.delete(note).test()

        // THEN
        testObserver.assertComplete()

        verify(noteDao).delete(noteEntity)
        verify(testSubject).delete(capture(noteArgumentCaptor))
        assertThat(noteArgumentCaptor.value.noteText, Matchers.`is`(note.noteText))
    }
}