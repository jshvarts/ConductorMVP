package com.jshvarts.conductormvp.notes

import com.jshvarts.conductormvp.mvp.BasePresenter
import com.jshvarts.conductormvp.domain.model.Note

/**
 * MVP Contract for displaying list of notes.
 */
interface NotesContract {

    interface View {
        fun displayNotes(notes: List<Note>)
        fun onUnableToLoadNotes()
        fun onFabClicked()
    }

    interface Presenter : BasePresenter<NotesView> {
        fun loadNotes()
    }
}