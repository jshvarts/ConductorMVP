package com.jshvarts.conductormvp.notedetail

import com.jshvarts.conductormvp.domain.NoteRepository
import com.jshvarts.conductormvp.domain.model.Note
import com.jshvarts.conductormvp.mvp.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class NoteDetailPresenter @Inject constructor(private val repository: NoteRepository) : BasePresenter<NoteDetailView>(), NoteDetailContract.Presenter{

    override fun loadNote(id: Long) {
        disposables.add(repository.findNoteById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { view?.showLoadingIndicator() }
                .doFinally { view?.hideLoadingIndicator() }
                .subscribe(this::onLoadNoteSuccess, this::onLoadNoteError))
    }

    override fun deleteNote(id: Long) {
        disposables.add(repository.delete(Note(id))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onDeleteNoteSuccess, this::onDeleteNoteError))
    }

    private fun onLoadNoteSuccess(note: Note) {
        view?.onLoadNoteSuccess(note)
    }

    private fun onLoadNoteError(error: Throwable) {
        Timber.e(error)
        view?.onLoadNoteError()
    }

    private fun onDeleteNoteSuccess() {
        view?.onDeleteNoteSuccess()
    }

    private fun onDeleteNoteError(error: Throwable) {
        Timber.e(error)
        view?.onDeleteNoteError()
    }
}