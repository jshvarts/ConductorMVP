package com.jshvarts.conductormvp.notedetail

import com.jshvarts.conductormvp.domain.model.Note
import com.jshvarts.conductormvp.mvp.MvpView

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