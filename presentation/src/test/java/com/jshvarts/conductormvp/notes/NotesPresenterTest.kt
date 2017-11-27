package com.jshvarts.conductormvp.notes

import com.jshvarts.notedomain.model.Note
import com.jshvarts.notedomain.repository.NoteRepository
import com.jshvarts.notedomain.usecases.NotesUseCase
import com.nhaarman.mockito_kotlin.*
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.powermock.reflect.Whitebox

class NotesPresenterTest {

    private lateinit var testSubject: NotesPresenter

    private val view: NotesView = mock()

    private val repository: NoteRepository = mock()

    private val notesUseCase: NotesUseCase = NotesUseCase(repository)

    private val testScheduler = TestScheduler()

    private val notes = arrayListOf(Note(1L, "some text"))

    @Before
    fun setUp() {
        RxJavaPlugins.setIoSchedulerHandler({ _ -> testScheduler })
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { _ -> Schedulers.trampoline() }

        testSubject = NotesPresenter(notesUseCase)
        testSubject.start(view)
    }

    @Test
    fun loadNotes_givenLoadNotesSuccess_callsViewOnLoadNotesSuccess() {
        // GIVEN
        whenever(repository.getAllNotes()).thenReturn(Single.just(notes))

        // WHEN
        testSubject.loadNotes()
        testScheduler.triggerActions()

        // THEN
        verify(view).onLoadNotesSuccess(notes)
    }

    @Test
    fun loadNotes_givenLoadNotesSuccess_andViewNotAttached_doesNotCallViewOnLoadNotesSuccess() {
        // GIVEN
        whenever(repository.getAllNotes()).thenReturn(Single.just(notes))
        testSubject.stop()

        // WHEN
        testSubject.loadNotes()
        testScheduler.triggerActions()

        // THEN
        verify(view, never()).onLoadNotesSuccess(notes)
    }

    @Test
    fun loadNotes_givenLoadNotesError_callsViewOnLoadNotesError() {
        // GIVEN
        val throwable = RuntimeException()
        whenever(repository.getAllNotes()).thenReturn(Single.error(throwable))

        // WHEN
        testSubject.loadNotes()
        testScheduler.triggerActions()

        // THEN
        verify(view).onLoadNotesError(throwable)
    }

    @Test
    fun loadNotes_givenLoadNotesError_andViewNotAttached_doesNotCallViewOnLoadNotesError() {
        // GIVEN
        val throwable = RuntimeException()
        whenever(repository.getAllNotes()).thenReturn(Single.error(throwable))
        testSubject.stop()

        // WHEN
        testSubject.loadNotes()
        testScheduler.triggerActions()

        // THEN
        verify(view, never()).onLoadNotesError(throwable)
    }

    @Test
    fun loadNotes_addsDisposable() {
        // GIVEN
        val disposables: CompositeDisposable = mock()
        Whitebox.setInternalState(testSubject, "disposables", disposables)
        whenever(repository.getAllNotes()).thenReturn(Single.just(notes))

        // WHEN
        testSubject.loadNotes()
        testScheduler.triggerActions()

        // THEN
        verify(disposables).add(any())
    }
}