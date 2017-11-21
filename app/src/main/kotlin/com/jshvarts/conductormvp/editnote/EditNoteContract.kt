package com.jshvarts.conductormvp.editnote

import com.jshvarts.notedomain.Note
import com.jshvarts.conductormvp.mvp.MvpView

/**
 * MVP Contract for Edit a Note screen.
 */
interface EditNoteContract {

    interface View : MvpView {
        fun onLoadNoteSuccess(note: Note)
        fun onEditNoteAction(code: Int): Boolean
        fun onEditNoteSuccess()
        fun onEditNoteError()
        fun onNoteValidationFailed()
        fun onNoteLookupFailed()
    }

    interface Presenter {
        fun loadNote(id: Long)
        fun editNote(id: Long, noteText: String)
    }
}