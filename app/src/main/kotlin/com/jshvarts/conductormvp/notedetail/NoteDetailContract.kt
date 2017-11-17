package com.jshvarts.conductormvp.notedetail

import com.jshvarts.conductormvp.mvp.BasePresenter
import com.jshvarts.conductormvp.model.Note

/**
 * MVP Contract for displaying list of notes.
 */
interface NoteDetailContract {

    interface View {
        fun displayNote(note: Note)
        fun onUnableToLoadNote()
        fun showProgressBar()
        fun hideProgressBar()
    }

    interface Presenter : BasePresenter<NoteDetailView> {
        fun loadNote(id: Long)
    }
}