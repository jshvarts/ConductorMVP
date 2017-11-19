package com.jshvarts.conductormvp.addnote

import com.jshvarts.conductormvp.model.Note
import com.jshvarts.conductormvp.mvp.BasePresenter

/**
 * MVP Contract for Add a Note screen.
 */
interface EditNoteContract {

    interface View {
        fun displayNoteForEdit(note: Note)
        fun onEditNoteAction(code: Int): Boolean
        fun onNoteEditSuccess()
        fun onNoteEditFailed()
        fun onNoteValidationFailed()
        fun onNoteLookupFailed()
    }

    interface Presenter : BasePresenter<EditNoteView> {
        fun loadNote(id: Long)
        fun editNote(id: Long, noteText: String)
    }
}