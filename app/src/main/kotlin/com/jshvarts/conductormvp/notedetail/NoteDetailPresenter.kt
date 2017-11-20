package com.jshvarts.conductormvp.notedetail

import com.jshvarts.conductormvp.domain.NoteRepository
import com.jshvarts.conductormvp.domain.model.Note
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class NoteDetailPresenter @Inject constructor(private val repository: NoteRepository) : NoteDetailContract.Presenter{

    private lateinit var view: NoteDetailView

    private val disposables: CompositeDisposable = CompositeDisposable()

    override fun detachView() {
        Timber.d("NoteDetailPresenter::detachView")
        disposables.clear()
    }

    override fun attachView(view: NoteDetailView) {
        Timber.d("NoteDetailPresenter::attachView")
        this.view = view
    }

    override fun loadNote(id: Long) {
        disposables.add(repository.findNoteById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { view.showLoadingIndicator() }
                .doFinally { view.hideLoadingIndicator() }
                .subscribe(view::displayNote, this::showUnableToLoadNoteError))
    }

    override fun deleteNote(id: Long) {
        disposables.add(repository.delete(Note(id))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(view::onDeleteNoteSuccess, this::showUnableToDeleteNoteError))
    }

    private fun showUnableToLoadNoteError(error: Throwable) {
        Timber.e(error)
        view.onUnableToLoadNote()
    }

    private fun showUnableToDeleteNoteError(error: Throwable) {
        Timber.e(error)
        view.onDeleteNoteFailed()
    }
}