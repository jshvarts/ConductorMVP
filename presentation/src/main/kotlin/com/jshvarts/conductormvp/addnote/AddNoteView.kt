package com.jshvarts.conductormvp.addnote

import android.support.design.widget.TextInputEditText
import android.view.View
import android.view.inputmethod.EditorInfo
import butterknife.BindView
import butterknife.OnEditorAction
import com.jshvarts.conductormvp.NotesApp
import com.jshvarts.conductormvp.R
import com.jshvarts.conductormvp.mvp.BaseView
import javax.inject.Inject

class AddNoteView : BaseView(), AddNoteContract.View {

    @Inject
    lateinit var presenter: AddNotePresenter

    @BindView(R.id.add_note_edit_text)
    lateinit var addNoteEditText: TextInputEditText

    override fun onAttach(view: View) {
        super.onAttach(view)

        DaggerAddNoteComponent.builder()
                .appComponent(NotesApp.component)
                .addNoteModule(AddNoteModule())
                .build()
                .inject(this)

        presenter.start(this)
    }

    override fun onDetach(view: View) {
        super.onDetach(view)
        presenter.stop()
    }

    override fun onDestroy() {
        presenter.destroy()
        super.onDestroy()
    }

    @OnEditorAction(R.id.add_note_edit_text)
    override fun onAddNoteAction(actionId: Int): Boolean {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            this.view?.hideKeyboard()
            presenter.addNote(addNoteEditText.text.toString())
            return true
        }
        return false
    }

    override fun onAddNoteSuccess() {
        showMessage(R.string.note_add_success)
        router.popCurrentController()
    }

    override fun onNoteValidationFailed() {
        showMessage(R.string.note_add_validation_failed)
    }

    override fun onAddNoteError() {
        showMessage(R.string.note_add_failed)
    }

    override fun getLayoutId() = R.layout.add_note

    override fun getToolbarTitleId() = R.string.screen_title_add_note
}