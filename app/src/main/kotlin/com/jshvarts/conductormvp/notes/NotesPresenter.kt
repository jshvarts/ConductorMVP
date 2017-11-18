package com.jshvarts.conductormvp.notes

import com.jshvarts.conductormvp.domain.NoteRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class NotesPresenter @Inject constructor(private val repository: NoteRepository) : NotesContract.Presenter {

    private lateinit var view: NotesView

    private val disposables: CompositeDisposable = CompositeDisposable()

    override fun detachView() {
        Timber.d("NotesPresenter::detachView")
        disposables.clear()
    }

    override fun attachView(view: NotesView) {
        Timber.d("NotesPresenter::attachView")
        this.view = view
    }

    override fun loadNotes() {
        disposables.add(repository.getAllNotes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(view::displayNotes, this::showUnableToLoadNotesError))
    }

    private fun showUnableToLoadNotesError(error: Throwable) {
        Timber.e(error)
        view.onUnableToLoadNotes()
    }
}