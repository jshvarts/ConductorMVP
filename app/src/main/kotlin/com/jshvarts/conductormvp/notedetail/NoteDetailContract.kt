package com.jshvarts.conductormvp.notedetail

import com.jshvarts.conductormvp.mvp.MvpView
import com.jshvarts.notedomain.model.Note

/**
 * MVP Contract for displaying note details and offering actions such as edit and delete.
 */
interface NoteDetailContract {

    interface View : MvpView {
        fun onLoadNoteSuccess(note: Note)
        fun onLoadNoteError()
        fun onDeleteNoteSuccess()
        fun onDeleteNoteError()
        fun onEditNoteButtonClicked()
        fun onDeleteNoteButtonClicked()
        fun showLoadingIndicator()
        fun hideLoadingIndicator()
    }

    interface Presenter {
        fun loadNote(id: Long)
        fun deleteNote(id: Long)
    }
}