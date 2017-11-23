package com.jshvarts.conductormvp.notedetail

import com.jshvarts.conductormvp.mvp.BasePresenter
import com.jshvarts.notedomain.Note
import com.jshvarts.notedomain.NoteRepository
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
                .doOnError(Timber::e)
                .subscribe({ view?.onLoadNoteSuccess(it) }, { view?.onLoadNoteError() }))
    }

    override fun deleteNote(id: Long) {
        disposables.add(repository.delete(Note(id))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(Timber::e)
                .subscribe({ view?.onDeleteNoteSuccess() }, { view?.onDeleteNoteError() }))
    }
}