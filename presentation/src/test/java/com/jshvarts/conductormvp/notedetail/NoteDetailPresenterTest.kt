package com.jshvarts.conductormvp.notedetail

import com.jshvarts.notedomain.model.Note
import com.jshvarts.notedomain.repository.NoteRepository
import com.jshvarts.notedomain.usecases.DeleteNoteUseCase
import com.jshvarts.notedomain.usecases.NoteDetailUseCase
import com.nhaarman.mockito_kotlin.*
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.inOrder
import org.powermock.reflect.Whitebox

class NoteDetailPresenterTest {

    private lateinit var testSubject: NoteDetailPresenter

    private val view: NoteDetailView = mock()

    private val repository: NoteRepository = mock()

    private val noteDetailUseCase: NoteDetailUseCase = NoteDetailUseCase(repository)

    private val deleteNoteUseCase: DeleteNoteUseCase = DeleteNoteUseCase(repository)

    private val testScheduler = TestScheduler()

    private val note = Note(1L, "some text")

    @Before
    fun setUp() {
        RxJavaPlugins.setIoSchedulerHandler({ _ -> testScheduler })
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { _ -> Schedulers.trampoline() }

        testSubject = NoteDetailPresenter(noteDetailUseCase, deleteNoteUseCase)
        testSubject.start(view)
    }

    @Test
    fun loadNote_givenFindNoteByIdSuccess_callsViewOnLoadNoteSuccess() {
        // GIVEN
        whenever(repository.findNoteById(note.id)).thenReturn(Maybe.just(note))

        // WHEN
        testSubject.loadNote(note.id)
        testScheduler.triggerActions()

        // THEN
        verify(view).onLoadNoteSuccess(note)
    }

    @Test
    fun loadNote_givenFindNoteByIdSuccess_andViewNotAttached_doesNotCallViewOnLoadNoteSuccess() {
        // GIVEN
        whenever(repository.findNoteById(note.id)).thenReturn(Maybe.just(note))
        testSubject.stop()

        // WHEN
        testSubject.loadNote(note.id)
        testScheduler.triggerActions()

        // THEN
        verify(view, never()).onLoadNoteSuccess(note)
    }

    @Test
    fun loadNote_givenFindNoteByIdError_callsViewOnLoadNoteError() {
        // GIVEN
        val throwable = RuntimeException()
        whenever(repository.findNoteById(note.id)).thenReturn(Maybe.error(throwable))

        // WHEN
        testSubject.loadNote(note.id)
        testScheduler.triggerActions()

        // THEN
        verify(view).onLoadNoteError(throwable)
    }

    @Test
    fun loadNote_givenFindNoteByIdError_andViewNotAttached_doesNotCallViewOnLoadNoteError() {
        // GIVEN
        val throwable = RuntimeException()
        whenever(repository.findNoteById(note.id)).thenReturn(Maybe.error(throwable))
        testSubject.stop()

        // WHEN
        testSubject.loadNote(note.id)
        testScheduler.triggerActions()

        // THEN
        verify(view, never()).onLoadNoteError(throwable)
    }

    @Test
    fun loadNote_callsViewShowLoading_andThenHideLoading() {
        // GIVEN
        whenever(repository.findNoteById(note.id)).thenReturn(Maybe.just(note))

        // WHEN
        testSubject.loadNote(note.id)
        testScheduler.triggerActions()

        // THEN
        val inOrder = inOrder(view, view)
        inOrder.verify(view).showLoading()
        inOrder.verify(view).hideLoading()
    }

    @Test
    fun loadNote_addsDisposable() {
        // GIVEN
        val disposables: CompositeDisposable = mock()
        Whitebox.setInternalState(testSubject, "disposables", disposables)
        whenever(repository.findNoteById(note.id)).thenReturn(Maybe.just(note))

        // WHEN
        testSubject.loadNote(note.id)
        testScheduler.triggerActions()

        // THEN
        verify(disposables).add(any())
    }

    @Test
    fun deleteNote_givenDeleteNoteSuccess_callsViewOnDeleteNoteSuccess() {
        // GIVEN
        whenever(repository.delete(any())).thenReturn(Completable.complete())

        // WHEN
        testSubject.deleteNote(note.id)
        testScheduler.triggerActions()

        // THEN
        verify(view).onDeleteNoteSuccess()
    }

    @Test
    fun deleteNote_givenDeleteNoteSuccess_andViewNotAttached_doesNotCallViewOnDeleteNoteSuccess() {
        // GIVEN
        whenever(repository.delete(any())).thenReturn(Completable.complete())
        testSubject.stop()

        // WHEN
        testSubject.deleteNote(note.id)
        testScheduler.triggerActions()

        // THEN
        verify(view, never()).onDeleteNoteSuccess()
    }

    @Test
    fun deleteNote_givenDeleteNoteError_callsViewOnDeleteNoteError() {
        // GIVEN
        val throwable = RuntimeException()
        whenever(repository.delete(any())).thenReturn(Completable.error(throwable))

        // WHEN
        testSubject.deleteNote(note.id)
        testScheduler.triggerActions()

        // THEN
        verify(view).onDeleteNoteError(throwable)
    }

    @Test
    fun deleteNote_givenDeleteNoteError_andViewNotAttached_doesNotCallViewOnDeleteNoteError() {
        // GIVEN
        val throwable = RuntimeException()
        whenever(repository.delete(any())).thenReturn(Completable.error(throwable))
        testSubject.stop()

        // WHEN
        testSubject.deleteNote(note.id)
        testScheduler.triggerActions()

        // THEN
        verify(view, never()).onDeleteNoteError(throwable)
    }

    @Test
    fun deleteNote_addsDisposable() {
        // GIVEN
        val disposables: CompositeDisposable = mock()
        Whitebox.setInternalState(testSubject, "disposables", disposables)
        whenever(repository.delete(any())).thenReturn(Completable.complete())

        // WHEN
        testSubject.deleteNote(note.id)
        testScheduler.triggerActions()

        // THEN
        verify(disposables).add(any())
    }
}