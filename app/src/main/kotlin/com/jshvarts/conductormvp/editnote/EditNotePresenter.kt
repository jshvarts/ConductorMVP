package com.jshvarts.conductormvp.editnote

import com.jshvarts.conductormvp.mvp.BasePresenter
import com.jshvarts.notedomain.NoteRepository
import com.jshvarts.notedomain.Note
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
        val note = Note(id, noteText)
        if (!note.isValid()) {
            view?.onNoteValidationFailed()
            return
        }
        disposables.add(repository.update(note)
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
        view?.onEditNoteError()
    }
}