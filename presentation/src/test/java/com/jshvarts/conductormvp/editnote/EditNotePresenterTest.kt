package com.jshvarts.conductormvp.editnote

import com.jshvarts.notedomain.usecases.EditNoteUseCase
import com.jshvarts.notedomain.usecases.NoteDetailUseCase
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class EditNotePresenterTest {

    @InjectMocks
    private lateinit var testSubject: EditNotePresenter

    @Mock
    private lateinit var noteDetailUseCase: NoteDetailUseCase

    @Mock
    private lateinit var editNoteUseCase: EditNoteUseCase

    @Mock
    private lateinit var view: EditNoteView

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        testSubject.start(view)
    }

    @Test
    fun onEditNoteError_givenErrorIsIllegalArgumentException_callsViewValidationFailed() {
        // GIVEN
        val error = IllegalArgumentException()

        // WHEN
        testSubject.onEditNoteError(error)

        // THEN
        verify(view).onNoteValidationFailed()
    }

    @Test
    fun onEditNoteError_givenErrorIsNotIllegalArgumentException_callsViewOnEditNoteError() {
        // GIVEN
        val error: Throwable = NullPointerException()

        // WHEN
        testSubject.onEditNoteError(error)

        // THEN
        verify(view).onEditNoteError()
    }
}