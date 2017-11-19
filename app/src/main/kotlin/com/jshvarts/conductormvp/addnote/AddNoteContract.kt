package com.jshvarts.conductormvp.addnote

import com.jshvarts.conductormvp.mvp.BasePresenter

/**
 * MVP Contract for Add a Note screen.
 */
interface AddNoteContract {

    interface View {
        fun onAddNoteAction(code: Int): Boolean
        fun onNoteAddSuccess()
        fun onNoteAddFailed()
        fun onNoteValidationFailed()
    }

    interface Presenter : BasePresenter<AddNoteView> {
        fun addNote(noteText: String)
    }
}