package com.jshvarts.conductormvp.mvp

import com.jshvarts.conductormvp.notes.NotesView
import com.nhaarman.mockito_kotlin.spy
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test

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
    }
}

class BasePresenterUnderTest : BasePresenter<NotesView>()