package com.jshvarts.conductormvp.notes

import com.jshvarts.conductormvp.mvp.MvpView
import com.jshvarts.notedomain.model.Note

/**
 * MVP Contract for displaying list of notes.
 */
interface NotesContract {

    interface View : MvpView {
        fun onLoadNotesSuccess(notes: List<Note>)
        fun onLoadNotesError()
        fun onFabClicked()
    }

    interface Presenter {
        fun loadNotes()
    }
}