package com.jshvarts.conductormvp.notedetail

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import butterknife.BindView
import butterknife.OnClick
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler
import com.jshvarts.conductormvp.NotesApp
import com.jshvarts.conductormvp.R
import com.jshvarts.conductormvp.editnote.EditNoteView
import com.jshvarts.conductormvp.mvp.BaseView
import com.jshvarts.notedomain.Note
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

    override fun getToolbarTitleId() = R.string.screen_title_note_detail

    override fun onAttach(view: View) {
        super.onAttach(view)

        DaggerNoteDetailComponent.builder()
                .appComponent(NotesApp.component)
                .noteDetailModule(NoteDetailModule())
                .build()
                .inject(this)

        presenter.attachView(this)
        presenter.loadNote(args.getLong(EXTRA_NOTE_ID))
    }

    override fun onDetach(view: View) {
        super.onDetach(view)
        presenter.detachView()
    }

    override fun onDestroy() {
        presenter.destroy()
        super.onDestroy()
    }

    @OnClick(R.id.edit_note_button)
    override fun onEditNoteButtonClicked() {
        val editNoteView = EditNoteView()
        editNoteView.args.putLong(EditNoteView.EXTRA_NOTE_ID, args.getLong(EXTRA_NOTE_ID))

        router.pushController(RouterTransaction.with(editNoteView)
                .pushChangeHandler(FadeChangeHandler())
                .popChangeHandler(FadeChangeHandler()))
    }

    @OnClick(R.id.delete_note_button)
    override fun onDeleteNoteButtonClicked() {
        presenter.deleteNote(args.getLong(EXTRA_NOTE_ID))
    }

    override fun onLoadNoteSuccess(note: Note) {
        noteText.text = note.noteText
    }

    override fun onLoadNoteError() {
        showMessage(R.string.note_detail_load_error)
    }

    override fun showLoadingIndicator() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoadingIndicator() {
        progressBar.visibility = View.GONE
    }

    override fun onDeleteNoteSuccess() {
        showMessage(R.string.note_detail_delete_note_success)
        router.popCurrentController()
    }

    override fun onDeleteNoteError() {
        showMessage(R.string.note_detail_delete_note_failed)
    }
}