package com.jshvarts.conductormvp.notedetail

import com.jshvarts.notedomain.model.Note

/**
 * MVP Contract for displaying note details and offering actions such as edit and delete.
 */
interface NoteDetailContract {

    interface View {
        fun onLoadNoteSuccess(note: Note)
        fun onLoadNoteError(throwable: Throwable)
        fun onDeleteNoteSuccess()
        fun onDeleteNoteError(throwable: Throwable)
        fun onEditNoteButtonClicked()
        fun onDeleteNoteButtonClicked()
        fun showLoading()
        fun hideLoading()
    }

    interface Presenter {
        fun loadNote(id: Long)
        fun deleteNote(id: Long)
    }
}