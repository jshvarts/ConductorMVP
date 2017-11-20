package com.jshvarts.conductormvp.notedetail

import com.jshvarts.conductormvp.domain.model.Note
import com.jshvarts.conductormvp.mvp.BasePresenter

/**
 * MVP Contract for displaying note details and offering actions such as edit and delete.
 */
interface NoteDetailContract {

    interface View {
        fun displayNote(note: Note)
        fun onUnableToLoadNote()
        fun showLoadingIndicator()
        fun hideLoadingIndicator()
        fun onEditNoteButtonClicked()
        fun onDeleteNoteButtonClicked()
        fun onDeleteNoteSuccess()
        fun onDeleteNoteFailed()
    }

    interface Presenter : BasePresenter<NoteDetailView> {
        fun loadNote(id: Long)
        fun deleteNote(id: Long)
    }
}