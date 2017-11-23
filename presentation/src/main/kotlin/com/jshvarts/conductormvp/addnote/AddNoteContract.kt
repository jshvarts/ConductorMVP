package com.jshvarts.conductormvp.addnote

import com.jshvarts.conductormvp.mvp.MvpView

/**
 * MVP Contract for Add a Note screen.
 */
interface AddNoteContract {

    interface View : MvpView {
        fun onAddNoteAction(code: Int): Boolean
        fun onAddNoteSuccess()
        fun onAddNoteError()
        fun onNoteValidationFailed()
    }

    interface Presenter {
        fun addNote(noteText: String)
    }
}