package com.jshvarts.conductormvp.mvp

import com.jshvarts.conductormvp.notes.NotesView
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.spy
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.disposables.CompositeDisposable
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.powermock.reflect.Whitebox

class BasePresenterTest {

    private lateinit var testSubject: BasePresenterUnderTest

    @Before
    fun setUp() {
        testSubject = spy(BasePresenterUnderTest())
    }

    @Test
    fun start_setsView() {
        // GIVEN
        val view = NotesView()

        // WHEN
        testSubject.start(view)

        // THEN
        verify(testSubject).start(view)
        assertTrue(Whitebox.getInternalState<BaseView>(testSubject, "view") != null)
    }

    @Test
    fun stop_nullifiesView() {
        // WHEN
        testSubject.stop()

        // THEN
        assertTrue(Whitebox.getInternalState<BaseView>(testSubject, "view") == null)
    }

    @Test
    fun destroy() {
        // GIVEN
        val disposables: CompositeDisposable = mock()
        Whitebox.setInternalState(testSubject, "disposables", disposables)

        // WHEN
        testSubject.destroy()

        // THEN
        verify(disposables).clear()
        assertTrue(Whitebox.getInternalState<CompositeDisposable>(testSubject, "disposables").size() == 0)
    }
}

class BasePresenterUnderTest : BasePresenter<NotesView>()