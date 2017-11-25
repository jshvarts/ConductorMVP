package com.jshvarts.conductormvp.notes

import com.jshvarts.conductormvp.mvp.BasePresenter
import com.jshvarts.notedomain.usecases.NotesUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class NotesPresenter @Inject constructor(private val notesUseCase: NotesUseCase) : BasePresenter<NotesView>(), NotesContract.Presenter {

    override fun loadNotes() {
        disposables.add(notesUseCase.loadNotes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(Timber::e)
                .subscribe({ view?.onLoadNotesSuccess(it) }, { view?.onLoadNotesError() }))
    }
}