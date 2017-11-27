package com.jshvarts.conductormvp.addnote

import com.jshvarts.notedomain.repository.NoteRepository
import com.jshvarts.notedomain.usecases.AddNoteUseCase
import com.nhaarman.mockito_kotlin.*
import io.reactivex.Completable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test

class AddNotePresenterTest {
    private lateinit var testSubject: AddNotePresenter

    private val view: AddNoteView = mock()

    private val repository: NoteRepository = mock()

    private val addNoteUseCase: AddNoteUseCase = AddNoteUseCase(repository)

    private val testScheduler = TestScheduler()

    private val validNoteText = "some text"

    private val invalidNoteText = ""

    @Before
    fun setUp() {
        RxJavaPlugins.setIoSchedulerHandler({ _ -> testScheduler })
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { _ -> Schedulers.trampoline() }

        testSubject = AddNotePresenter(addNoteUseCase)
        testSubject.start(view)
    }

    @Test
    fun addNote_givenAddNoteSuccess_callsViewOnAddNoteSuccess() {
        // GIVEN
        whenever(repository.insertOrUpdate(any())).thenReturn(Completable.complete())

        // WHEN
        testSubject.addNote(validNoteText)
        testScheduler.triggerActions()

        // THEN
        verify(view).onAddNoteSuccess()
    }

    @Test
    fun addNote_givenAddNoteSuccess_andViewNotAttached_doesNotCallViewOnAddNoteSuccess() {
        // GIVEN
        whenever(repository.insertOrUpdate(any())).thenReturn(Completable.complete())
        testSubject.stop()

        // WHEN
        testSubject.addNote(validNoteText)
        testScheduler.triggerActions()

        // THEN
        verify(view, never()).onAddNoteSuccess()
    }

    @Test
    fun addNote_givenAddNoteError_callsViewOnAddNoteError() {
        // GIVEN
        val throwable = RuntimeException()
        whenever(repository.insertOrUpdate(any())).thenReturn(Completable.error(throwable))

        // WHEN
        testSubject.addNote(validNoteText)
        testScheduler.triggerActions()

        // THEN
        verify(view).onAddNoteError(throwable)
    }

    @Test
    fun addNote_givenAddNoteError_andViewNotAttached_doesNotCallOnViewAddNoteError() {
        // GIVEN
        val throwable = RuntimeException()
        whenever(repository.insertOrUpdate(any())).thenReturn(Completable.error(throwable))
        testSubject.stop()

        // WHEN
        testSubject.addNote(validNoteText)
        testScheduler.triggerActions()

        // THEN
        verify(view, never()).onAddNoteError(throwable)
    }

    @Test
    fun addNote_givenNoteValidationFails_callsViewOnNoteValidationFailed() {
        // GIVEN
        whenever(repository.insertOrUpdate(any())).thenReturn(Completable.complete())

        // WHEN
        testSubject.addNote(invalidNoteText)
        testScheduler.triggerActions()

        // THEN
        verify(view).onNoteValidationFailed()
    }

    @Test
    fun addNote_givenNoteValidationFails_andViewNotAttached_doesNotCallViewOnNoteValidationFailed() {
        // GIVEN
        whenever(repository.insertOrUpdate(any())).thenReturn(Completable.complete())
        testSubject.stop()

        // WHEN
        testSubject.addNote(invalidNoteText)
        testScheduler.triggerActions()

        // THEN
        verify(view, never()).onNoteValidationFailed()
    }
}