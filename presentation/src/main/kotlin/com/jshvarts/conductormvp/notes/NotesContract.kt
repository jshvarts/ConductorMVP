package com.jshvarts.conductormvp.notes

import com.jshvarts.notedomain.Note

/**
 * MVP Contract for displaying list of notes.
 */
interface NotesContract {

    interface View {
        fun onLoadNotesSuccess(notes: List<Note>)
        fun onLoadNotesError()
        fun onFabClicked()
    }

    interface Presenter {
        fun loadNotes()
    }
}