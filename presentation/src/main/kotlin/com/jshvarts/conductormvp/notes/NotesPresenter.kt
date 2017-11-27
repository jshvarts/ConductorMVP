package com.jshvarts.conductormvp.notes

import com.jshvarts.conductormvp.mvp.BasePresenter
import com.jshvarts.notedomain.usecases.NotesUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NotesPresenter @Inject constructor(private val notesUseCase: NotesUseCase) : BasePresenter<NotesView>(), NotesContract.Presenter {

    override fun loadNotes() {
        disposables.add(notesUseCase.loadNotes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ view?.onLoadNotesSuccess(it) }, { view?.onLoadNotesError(it) }))
    }
}