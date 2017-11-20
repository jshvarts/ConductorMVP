package com.jshvarts.conductormvp.addnote

import com.jshvarts.conductormvp.domain.NoteRepository
import com.jshvarts.conductormvp.domain.model.Note
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class AddNotePresenter @Inject constructor(private val repository: NoteRepository) : AddNoteContract.Presenter {

    private lateinit var view: AddNoteView

    private val disposables: CompositeDisposable = CompositeDisposable()

    override fun detachView() {
        Timber.d("AddNotePresenter::detachView")
        disposables.clear()
    }

    override fun attachView(view: AddNoteView) {
        Timber.d("AddNotePresenter::attachView")
        this.view = view
    }

    override fun addNote(noteText: String) {
        val note = Note(noteText = noteText)
        if (!note.isValid()) {
            view.onNoteValidationFailed()
            return
        }
        disposables.add(repository.add(note)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(view::onNoteAddSuccess, this::showUnableToAddNoteError))
    }

    private fun showUnableToAddNoteError(error: Throwable) {
        Timber.e(error)
        view.onNoteAddFailed()
    }
}