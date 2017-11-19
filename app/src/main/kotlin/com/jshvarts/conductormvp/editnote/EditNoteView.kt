package com.jshvarts.conductormvp.addnote

import android.support.design.widget.TextInputEditText
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import butterknife.BindView
import butterknife.OnEditorAction
import com.jshvarts.conductormvp.R
import com.jshvarts.conductormvp.app.NotesApp
import com.jshvarts.conductormvp.model.Note
import com.jshvarts.conductormvp.mvp.BaseView
import javax.inject.Inject

class EditNoteView : BaseView(), EditNoteContract.View {

    companion object {
        const val EXTRA_NOTE_ID = "EditNoteView.noteId"
    }

    @Inject
    lateinit var presenter: EditNotePresenter

    @BindView(R.id.edit_note_edit_text)
    lateinit var editNoteEditText: TextInputEditText

    lateinit var note: Note

    override fun onAttach(view: View) {

        DaggerEditNoteComponent.builder()
                .appComponent(NotesApp.component)
                .editNoteModule(EditNoteModule())
                .build()
                .inject(this)

        presenter.attachView(this)

        presenter.loadNote(args.getLong(EXTRA_NOTE_ID))
    }

    override fun displayNoteForEdit(note: Note) {
        this.note = note
        editNoteEditText.setText(note.noteText)
    }

    override fun onNoteEditSuccess() {
        router.popCurrentController()
    }

    override fun onNoteEditFailed() {
        Toast.makeText(this.applicationContext, R.string.note_edit_failed, Toast.LENGTH_LONG).show()
    }

    override fun onNoteLookupFailed() {
        Toast.makeText(this.applicationContext, R.string.note_edit_lookup_failed, Toast.LENGTH_LONG).show()
    }

    override fun onDetach(view: View) {
        super.onDetach(view)
        presenter.detachView()
    }

    override fun getLayoutId() = R.layout.edit_note

    @OnEditorAction(R.id.edit_note_edit_text)
    override fun onEditNoteAction(actionId: Int): Boolean {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            this.view?.hideKeyboard()
            presenter.editNote(note.id, editNoteEditText.text.toString())
            return true
        }
        return false
    }

    override fun onNoteValidationFailed() {
        Toast.makeText(this.applicationContext, R.string.note_add_validation_failed, Toast.LENGTH_LONG).show()
    }
}