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
import com.jshvarts.conductormvp.mvp.MvpPresenter
import com.jshvarts.notedomain.model.Note
import timber.log.Timber
import javax.inject.Inject

class NoteDetailView : BaseView(), NoteDetailContract.View {

    @Inject
    lateinit var presenter: NoteDetailPresenter

    @BindView(R.id.note_detail_text)
    lateinit var noteText: TextView

    @BindView(R.id.note_detail_progress_bar)
    lateinit var progressBar: ProgressBar

    override fun injectDependencies() {
        DaggerNoteDetailComponent.builder()
                .appComponent(NotesApp.component)
                .noteDetailModule(NoteDetailModule())
                .build()
                .inject(this)
    }

    override fun onAttach(view: View) {
        super.onAttach(view)
        with(presenter) {
            start(this@NoteDetailView)
            loadNote(args.getLong(KEY_NOTE_ID))
        }
    }

    @OnClick(R.id.edit_note_button)
    override fun onEditNoteButtonClicked() {
        val editNoteView = EditNoteView().apply {
            args.putLong(KEY_NOTE_ID, this@NoteDetailView.args.getLong(KEY_NOTE_ID))
        }

        router.pushController(RouterTransaction.with(editNoteView)
                .pushChangeHandler(FadeChangeHandler())
                .popChangeHandler(FadeChangeHandler()))
    }

    @OnClick(R.id.delete_note_button)
    override fun onDeleteNoteButtonClicked() {
        presenter.deleteNote(args.getLong(KEY_NOTE_ID))
    }

    override fun onLoadNoteSuccess(note: Note) {
        noteText.text = note.noteText
    }

    override fun onLoadNoteError(throwable: Throwable) {
        Timber.e(throwable)
        showMessage(R.string.note_detail_load_error)
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun onDeleteNoteSuccess() {
        showMessage(R.string.note_detail_delete_note_success)
        router.popCurrentController()
    }

    override fun onDeleteNoteError(throwable: Throwable) {
        Timber.e(throwable)
        showMessage(R.string.note_detail_delete_note_failed)
    }

    override fun getLayoutId() = R.layout.note_detail

    override fun getToolbarTitleId() = R.string.screen_title_note_detail

    override fun getPresenter(): MvpPresenter = presenter
}