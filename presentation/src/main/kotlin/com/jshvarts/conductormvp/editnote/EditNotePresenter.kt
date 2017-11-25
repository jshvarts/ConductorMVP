package com.jshvarts.conductormvp.editnote

import android.support.annotation.VisibleForTesting
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
                .doOnError(Timber::e)
                .subscribe({ view?.onLoadNoteSuccess(it) }, { view?.onNoteLookupError() }))
    }

    override fun editNote(id: Long, noteText: String) {
        disposables.add(repository.update(Note(id, noteText))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(Timber::e)
                .subscribe({ view?.onEditNoteSuccess() }, this::onEditNoteError))
    }

    @VisibleForTesting
    fun onEditNoteError(error: Throwable) {
        when(error) {
            is IllegalArgumentException -> view?.onNoteValidationFailed()
            else -> view?.onEditNoteError()
        }
    }
}