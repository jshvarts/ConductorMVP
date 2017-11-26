package com.jshvarts.conductormvp.editnote

import com.jshvarts.notedomain.model.Note
import com.jshvarts.notedomain.repository.NoteRepository
import com.jshvarts.notedomain.usecases.EditNoteUseCase
import com.jshvarts.notedomain.usecases.NoteDetailUseCase
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.Before
import org.junit.Test

class EditNotePresenterTest {

    private lateinit var testSubject: EditNotePresenter

    private val repository: NoteRepository = mock()

    private val noteDetailUseCase: NoteDetailUseCase = NoteDetailUseCase(repository)

    private val editNoteUseCase: EditNoteUseCase = EditNoteUseCase(repository)

    private val view: EditNoteView = mock()

    private val testScheduler = TestScheduler()

    private val validNote = Note(1L, "some text")

    @Before
    fun setUp() {
        testSubject = EditNotePresenter(noteDetailUseCase, editNoteUseCase)
        testSubject.start(view)
        RxJavaPlugins.setIoSchedulerHandler({ _ -> testScheduler })
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { _ -> Schedulers.trampoline() }
    }

    @After
    fun tearDown() {
        testSubject.stop()
        RxJavaPlugins.reset()
        RxAndroidPlugins.reset()
    }

    @Test
    fun loadNote_givenFindNoteByIdSuccess_callsViewOnLoadNoteSuccess() {
        // GIVEN
        whenever(repository.findNoteById(validNote.id)).thenReturn(Maybe.just(validNote))

        // WHEN
        testSubject.loadNote(validNote.id)
        testScheduler.triggerActions()

        // THEN
        verify(view).onLoadNoteSuccess(validNote)
    }

    @Test
    fun loadNote_givenFindNoteByIdError_callsViewOnNoteLookupError() {
        // GIVEN
        val noteId = 1L
        val error: Throwable = RuntimeException()
        whenever(repository.findNoteById(noteId)).thenReturn(Maybe.error(error))

        // WHEN
        testSubject.loadNote(noteId)
        testScheduler.triggerActions()

        // THEN
        verify(view).onNoteLookupError()
    }

    @Test
    fun editNote_givenValidNoteAndEditNoteSuccess_callsViewEditNoteSuccess() {
        // GIVEN
        whenever(repository.insertOrUpdate(validNote)).thenReturn(Completable.complete())

        // WHEN
        testSubject.editNote(validNote.id, validNote.noteText)
        testScheduler.triggerActions()

        // THEN
        verify(view).onEditNoteSuccess()
    }

    @Test
    fun editNote_givenValidNoteAndEditNoteError_callsViewEditNoteError() {
        // GIVEN
        val error: Throwable = RuntimeException()
        whenever(repository.insertOrUpdate(validNote)).thenReturn(Completable.error(error))

        // WHEN
        testSubject.editNote(validNote.id, validNote.noteText)
        testScheduler.triggerActions()

        // THEN
        verify(view).onEditNoteError()
    }

    @Test
    fun editNote_givenEditValidationFails_callsViewOnNoteValidationFailed() {
        // GIVEN
        val invalidId = 0L
        val note = Note(invalidId, "some text")
        whenever(repository.insertOrUpdate(note)).thenReturn(Completable.complete())

        // WHEN
        testSubject.editNote(note.id, note.noteText)
        testScheduler.triggerActions()

        // THEN
        verify(view).onNoteValidationFailed()
    }
}