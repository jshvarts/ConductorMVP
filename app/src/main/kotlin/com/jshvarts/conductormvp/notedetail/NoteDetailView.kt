package com.jshvarts.conductormvp.notedetail

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import butterknife.BindView
import butterknife.OnClick
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler
import com.jshvarts.conductormvp.R
import com.jshvarts.conductormvp.addnote.EditNoteView
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

    @BindView(R.id.note_detail_text)
    lateinit var noteText: TextView

    @BindView(R.id.note_detail_progress_bar)
    lateinit var progressBar: ProgressBar

    override fun getLayoutId() = R.layout.note_detail

    override fun onAttach(view: View) {

        DaggerNoteDetailComponent.builder()
                .appComponent(NotesApp.component)
                .noteDetailModule(NoteDetailModule())
                .build()
                .inject(this)

        presenter.attachView(this)
        presenter.loadNote(getCurrentNoteId())
    }

    @OnClick(R.id.note_detail_close_button)
    override fun onCloseNoteButtonClicked() {
        router.popCurrentController()
    }

    @OnClick(R.id.edit_note_button)
    override fun onEditNoteButtonClicked() {
        val editNoteView = EditNoteView().apply {
            args.putLong(EditNoteView.EXTRA_NOTE_ID, getCurrentNoteId())
        }

        router.pushController(RouterTransaction.with(editNoteView)
                .pushChangeHandler(FadeChangeHandler())
                .popChangeHandler(FadeChangeHandler()))
    }

    override fun displayNote(note: Note) {
        noteText.text = note.noteText
    }

    override fun onUnableToLoadNote() {
        Toast.makeText(this.applicationContext, R.string.note_detail_load_error, Toast.LENGTH_LONG).show()
    }

    override fun showLoadingIndicator() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoadingIndicator() {
        progressBar.visibility = View.GONE
    }

    private fun getCurrentNoteId() = args.getLong(EXTRA_NOTE_ID)
}