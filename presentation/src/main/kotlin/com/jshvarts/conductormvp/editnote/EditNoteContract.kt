package com.jshvarts.conductormvp.editnote

import com.jshvarts.notedomain.model.Note

/**
 * MVP Contract for Edit a Note screen.
 */
interface EditNoteContract {

    interface View {
        fun onLoadNoteSuccess(note: Note)
        fun onEditNoteAction(code: Int): Boolean
        fun onEditNoteSuccess()
        fun onEditNoteError(throwable: Throwable)
        fun onNoteLookupError(throwable: Throwable)
        fun onNoteValidationFailed(throwable: Throwable)
    }

    interface Presenter {
        fun loadNote(id: Long)
        fun editNote(id: Long, noteText: String)
    }
}