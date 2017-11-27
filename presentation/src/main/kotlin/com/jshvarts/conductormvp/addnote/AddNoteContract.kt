package com.jshvarts.conductormvp.addnote

/**
 * MVP Contract for Add a Note screen.
 */
interface AddNoteContract {

    interface View {
        fun onAddNoteAction(code: Int): Boolean
        fun onAddNoteSuccess()
        fun onAddNoteError(throwable: Throwable)
        fun onNoteValidationFailed()
    }

    interface Presenter {
        fun addNote(noteText: String)
    }
}