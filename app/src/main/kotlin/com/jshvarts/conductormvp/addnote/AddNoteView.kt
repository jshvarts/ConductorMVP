package com.jshvarts.conductormvp.addnote

import android.support.design.widget.TextInputEditText
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import butterknife.BindView
import butterknife.OnEditorAction
import com.jshvarts.conductormvp.R
import com.jshvarts.conductormvp.app.NotesApp
import com.jshvarts.conductormvp.mvp.BaseView
import javax.inject.Inject

class AddNoteView : BaseView(), AddNoteContract.View {

    @Inject
    lateinit var presenter: AddNotePresenter

    @BindView(R.id.add_note_edit_text)
    lateinit var addNoteEditText: TextInputEditText

    override fun onAttach(view: View) {

        DaggerAddNoteComponent.builder()
                .appComponent(NotesApp.component)
                .addNoteModule(AddNoteModule())
                .build()
                .inject(this)

        presenter.attachView(this)
    }

    override fun onDetach(view: View) {
        super.onDetach(view)
        presenter.detachView()
    }

    override fun getLayoutId() = R.layout.add_note

    @OnEditorAction(R.id.add_note_edit_text)
    override fun onAddNoteAction(actionId: Int): Boolean {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            this.view?.hideKeyboard()
            presenter.addNote(addNoteEditText.text.toString())
            return true
        }
        return false
    }

    override fun onNoteAddSuccess() {
        Toast.makeText(this.applicationContext, R.string.note_add_success, Toast.LENGTH_LONG).show()
        router.popCurrentController()
    }

    override fun onNoteValidationFailed() {
        Toast.makeText(this.applicationContext, R.string.note_add_validation_failed, Toast.LENGTH_LONG).show()
    }

    override fun onNoteAddFailed() {
        TODO("not implemented")
    }
}