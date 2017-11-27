package com.jshvarts.conductormvp.editnote

import com.jshvarts.notedomain.model.Note
import com.jshvarts.notedomain.repository.NoteRepository
import com.jshvarts.notedomain.usecases.EditNoteUseCase
import com.jshvarts.notedomain.usecases.NoteDetailUseCase
import com.nhaarman.mockito_kotlin.*
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test

class EditNotePresenterTest {

    private lateinit var testSubject: EditNotePresenter

    private val view: EditNoteView = mock()

    private val repository: NoteRepository = mock()

    private val noteDetailUseCase: NoteDetailUseCase = NoteDetailUseCase(repository)

    private val editNoteUseCase: EditNoteUseCase = EditNoteUseCase(repository)

    private val testScheduler = TestScheduler()

    private val validNote = Note(1L, "some text")

    private val invalidNote = Note(0L, "some text")

    @Before
    fun setUp() {
        RxJavaPlugins.setIoSchedulerHandler({ _ -> testScheduler })
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { _ -> Schedulers.trampoline() }

        testSubject = EditNotePresenter(noteDetailUseCase, editNoteUseCase)
        testSubject.start(view)
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
    fun loadNote_givenFindNoteByIdSuccess_andViewNotAttached_doesNotCallViewOnLoadNoteSuccess() {
        // GIVEN
        whenever(repository.findNoteById(validNote.id)).thenReturn(Maybe.just(validNote))
        testSubject.stop()

        // WHEN
        testSubject.loadNote(validNote.id)
        testScheduler.triggerActions()

        // THEN
        verify(view, never()).onLoadNoteSuccess(validNote)
    }

    @Test
    fun loadNote_givenFindNoteByIdError_callsViewOnNoteLookupError() {
        // GIVEN
        val throwable = RuntimeException()
        whenever(repository.findNoteById(invalidNote.id)).thenReturn(Maybe.error(throwable))

        // WHEN
        testSubject.loadNote(invalidNote.id)
        testScheduler.triggerActions()

        // THEN
        verify(view).onNoteLookupError(throwable)
    }

    @Test
    fun loadNote_givenFindNoteByIdError_andViewNotAttached_doesNotCallViewOnNoteLookupError() {
        // GIVEN
        val throwable = RuntimeException()
        whenever(repository.findNoteById(invalidNote.id)).thenReturn(Maybe.error(throwable))
        testSubject.stop()

        // WHEN
        testSubject.loadNote(invalidNote.id)
        testScheduler.triggerActions()

        // THEN
        verify(view, never()).onNoteLookupError(throwable)
    }

    @Test
    fun editNote_givenEditNoteSuccess_callsViewEditNoteSuccess() {
        // GIVEN
        whenever(repository.insertOrUpdate(validNote)).thenReturn(Completable.complete())

        // WHEN
        testSubject.editNote(validNote.id, validNote.noteText)
        testScheduler.triggerActions()

        // THEN
        verify(view).onEditNoteSuccess()
    }

    @Test
    fun editNote_givenEditNoteSuccess_andViewNotAttached_doesNotCallViewEditNoteSuccess() {
        // GIVEN
        whenever(repository.insertOrUpdate(validNote)).thenReturn(Completable.complete())
        testSubject.stop()

        // WHEN
        testSubject.editNote(validNote.id, validNote.noteText)
        testScheduler.triggerActions()

        // THEN
        verify(view, never()).onEditNoteSuccess()
    }

    @Test
    fun editNote_givenEditNoteError_callsViewEditNoteError() {
        // GIVEN
        val throwable = RuntimeException()
        whenever(repository.insertOrUpdate(validNote)).thenReturn(Completable.error(throwable))

        // WHEN
        testSubject.editNote(validNote.id, validNote.noteText)
        testScheduler.triggerActions()

        // THEN
        verify(view).onEditNoteError(throwable)
    }

    @Test
    fun editNote_givenEditNoteError_andViewNotAttached_doesNotCallViewEditNoteError() {
        // GIVEN
        val throwable = RuntimeException()
        whenever(repository.insertOrUpdate(validNote)).thenReturn(Completable.error(throwable))
        testSubject.stop()

        // WHEN
        testSubject.editNote(validNote.id, validNote.noteText)
        testScheduler.triggerActions()

        // THEN
        verify(view, never()).onEditNoteError(throwable)
    }

    @Test
    fun editNote_givenEditNoteValidationFails_callsViewOnNoteValidationFailed() {
        // GIVEN
        whenever(repository.insertOrUpdate(invalidNote)).thenReturn(Completable.complete())

        // WHEN
        testSubject.editNote(invalidNote.id, invalidNote.noteText)
        testScheduler.triggerActions()

        // THEN
        verify(view).onNoteValidationFailed(any())
    }

    @Test
    fun editNote_givenEditNoteValidationFails_andViewNotAttached_doesNotCallViewOnNoteValidationFailed() {
        // GIVEN
        whenever(repository.insertOrUpdate(invalidNote)).thenReturn(Completable.complete())
        testSubject.stop()

        // WHEN
        testSubject.editNote(invalidNote.id, invalidNote.noteText)
        testScheduler.triggerActions()

        // THEN
        verify(view, never()).onNoteValidationFailed(any())
    }
}