package com.jshvarts.conductormvp.addnote

import com.jshvarts.conductormvp.mvp.BasePresenter
import com.jshvarts.notedomain.Note
import com.jshvarts.notedomain.NoteRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class AddNotePresenter @Inject constructor(private val repository: NoteRepository) : BasePresenter<AddNoteView>(), AddNoteContract.Presenter {

    override fun addNote(noteText: String) {
        disposables.add(repository.add(Note(noteText = noteText))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onAddNoteSuccess, this::onAddNoteError))
    }

    private fun onAddNoteSuccess() {
        view?.onAddNoteSuccess()
    }

    private fun onAddNoteError(error: Throwable) {
        Timber.e(error)
        when(error) {
            is IllegalArgumentException -> view?.onNoteValidationFailed()
            else -> view?.onAddNoteError()
        }
    }
}