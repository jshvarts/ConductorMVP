package com.jshvarts.conductormvp.editnote

import com.jshvarts.notedomain.Note

/**
 * MVP Contract for Edit a Note screen.
 */
interface EditNoteContract {

    interface View {
        fun onLoadNoteSuccess(note: Note)
        fun onEditNoteAction(code: Int): Boolean
        fun onEditNoteSuccess()
        fun onEditNoteError()
        fun onNoteValidationFailed()
        fun onNoteLookupError()
    }

    interface Presenter {
        fun loadNote(id: Long)
        fun editNote(id: Long, noteText: String)
    }
}