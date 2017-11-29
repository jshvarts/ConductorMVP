package com.jshvarts.conductormvp.editnote

import android.support.design.widget.TextInputEditText
import android.view.View
import android.view.inputmethod.EditorInfo
import butterknife.BindView
import butterknife.OnEditorAction
import com.jshvarts.conductormvp.NotesApp
import com.jshvarts.conductormvp.R
import com.jshvarts.conductormvp.mvp.BaseView
import com.jshvarts.notedomain.model.Note
import timber.log.Timber
import javax.inject.Inject

class EditNoteView : BaseView(), EditNoteContract.View {

    @Inject
    lateinit var presenter: EditNotePresenter

    @BindView(R.id.edit_note_edit_text)
    lateinit var editNoteEditText: TextInputEditText

    override fun injectDependencies() {
        DaggerEditNoteComponent.builder()
                .appComponent(NotesApp.component)
                .editNoteModule(EditNoteModule())
                .build()
                .inject(this)
    }

    override fun onAttach(view: View) {
        super.onAttach(view)
        presenter.start(this)
        presenter.loadNote(args.getLong(KEY_NOTE_ID))
    }

    override fun onLoadNoteSuccess(note: Note) {
        editNoteEditText.setText(note.noteText)
    }

    override fun onEditNoteSuccess() {
        showMessage(R.string.note_edit_success)
        router.popCurrentController()
    }

    override fun onEditNoteError(throwable: Throwable) {
        Timber.e(throwable)
        showMessage(R.string.note_edit_failed)
    }

    override fun onNoteLookupError(throwable: Throwable) {
        Timber.e(throwable)
        showMessage(R.string.note_edit_lookup_failed)
    }

    override fun onDetach(view: View) {
        super.onDetach(view)
        presenter.stop()
    }

    override fun onDestroy() {
        presenter.destroy()
        super.onDestroy()
    }

    @OnEditorAction(R.id.edit_note_edit_text)
    override fun onEditNoteAction(code: Int): Boolean {
        if (code == EditorInfo.IME_ACTION_DONE) {
            this.view?.hideKeyboard()
            presenter.editNote(args.getLong(KEY_NOTE_ID), editNoteEditText.text.toString())
            return true
        }
        return false
    }

    override fun onNoteValidationFailed(throwable: Throwable) {
        Timber.w(throwable)
        showMessage(R.string.note_add_validation_failed)
    }

    override fun getLayoutId() = R.layout.edit_note

    override fun getToolbarTitleId() = R.string.screen_title_edit_note
}