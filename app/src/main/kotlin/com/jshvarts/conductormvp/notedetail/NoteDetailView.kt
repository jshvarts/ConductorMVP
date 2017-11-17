package com.jshvarts.conductormvp.notedetail

import android.view.View
import android.widget.TextView
import butterknife.BindView
import com.jshvarts.conductormvp.R
import com.jshvarts.conductormvp.app.NotesApp
import com.jshvarts.conductormvp.model.Note
import com.jshvarts.conductormvp.mvp.BaseView
import javax.inject.Inject

class NoteDetailView : BaseView(), NoteDetailContract.View {

    companion object {
        const val EXTRA_NOTE_ID = "NoteDetailView.noteId"
    }

    @Inject
    lateinit var presenter: NoteDetailPresenter

    @BindView(R.id.note_text)
    lateinit var noteText: TextView

    override fun getLayoutId() = R.layout.note_detail

    override fun onAttach(view: View) {

        DaggerNoteDetailComponent.builder()
                .appComponent(NotesApp.component)
                .noteDetailModule(NoteDetailModule())
                .build()
                .inject(this)

        val noteId = args.getLong(EXTRA_NOTE_ID)

        presenter.attachView(this)
        presenter.loadNote(noteId)
    }

    override fun displayNote(note: Note) {
        noteText.text = note.noteText
    }

    override fun onUnableToLoadNote() {
        TODO("not implemented")
    }

    override fun showProgressBar() {
        TODO("not implemented")
    }

    override fun hideProgressBar() {
        TODO("not implemented")
    }
}