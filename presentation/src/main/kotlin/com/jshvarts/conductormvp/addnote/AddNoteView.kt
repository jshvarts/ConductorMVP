package com.jshvarts.conductormvp.addnote

import android.support.design.widget.TextInputEditText
import android.view.View
import android.view.inputmethod.EditorInfo
import butterknife.BindView
import butterknife.OnEditorAction
import com.jshvarts.conductormvp.NotesApp
import com.jshvarts.conductormvp.R
import com.jshvarts.conductormvp.mvp.BaseView
import timber.log.Timber
import javax.inject.Inject

class AddNoteView : BaseView(), AddNoteContract.View {

    @Inject
    lateinit var presenter: AddNotePresenter

    @BindView(R.id.add_note_edit_text)
    lateinit var addNoteEditText: TextInputEditText

    override fun injectDependencies() {
        DaggerAddNoteComponent.builder()
                .appComponent(NotesApp.component)
                .addNoteModule(AddNoteModule())
                .build()
                .inject(this)
    }

    override fun onAttach(view: View) {
        super.onAttach(view)
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
    override fun onAddNoteAction(code: Int): Boolean {
        if (code == EditorInfo.IME_ACTION_DONE) {
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

    override fun onAddNoteError(throwable: Throwable) {
        Timber.e(throwable)
        showMessage(R.string.note_add_failed)
    }

    override fun getLayoutId() = R.layout.add_note

    override fun getToolbarTitleId() = R.string.screen_title_add_note
}