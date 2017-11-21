package com.jshvarts.conductormvp.notes

import com.jshvarts.conductormvp.domain.NoteRepository
import com.jshvarts.conductormvp.domain.model.Note
import com.jshvarts.conductormvp.mvp.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class NotesPresenter @Inject constructor(private val repository: NoteRepository) : BasePresenter<NotesView>(), NotesContract.Presenter {

    override fun loadNotes() {
        disposables.add(repository.getAllNotes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onLoadNotesSuccess, this::onLoadNotesError))
    }

    private fun onLoadNotesSuccess(notes: List<Note>) {
        view?.onLoadNotesSuccess(notes)
    }

    private fun onLoadNotesError(error: Throwable) {
        Timber.e(error)
        view?.onLoadNotesError()
    }
}