package com.jshvarts.conductormvp.editnote

import com.jshvarts.conductormvp.mvp.BasePresenter
import com.jshvarts.notedomain.Note
import com.jshvarts.notedomain.NoteRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class EditNotePresenter @Inject constructor(private val repository: NoteRepository) : BasePresenter<EditNoteView>(), EditNoteContract.Presenter {

    override fun loadNote(id: Long) {
        disposables.add(repository.findNoteById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onLoadNoteSuccess, this::onLoadNoteError))
    }

    override fun editNote(id: Long, noteText: String) {
        disposables.add(repository.update(Note(id, noteText))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onEditNoteSuccess, this::onEditNoteError))
    }

    private fun onLoadNoteSuccess(note: Note) {
        view?.onLoadNoteSuccess(note)
    }

    private fun onLoadNoteError(error: Throwable) {
        Timber.e(error)
        view?.onNoteLookupFailed()
    }

    private fun onEditNoteSuccess() {
        view?.onEditNoteSuccess()
    }

    private fun onEditNoteError(error: Throwable) {
        Timber.e(error)
        when(error) {
            is IllegalArgumentException -> view?.onNoteValidationFailed()
            else -> view?.onEditNoteError()
        }
    }
}