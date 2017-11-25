package com.jshvarts.conductormvp.notedetail

import com.jshvarts.conductormvp.mvp.BasePresenter
import com.jshvarts.notedomain.model.Note
import com.jshvarts.notedomain.usecases.DeleteNoteUseCase
import com.jshvarts.notedomain.usecases.NoteDetailUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class NoteDetailPresenter @Inject constructor(private val noteDetailUseCase: NoteDetailUseCase, private val deleteNoteUseCase: DeleteNoteUseCase) : BasePresenter<NoteDetailView>(), NoteDetailContract.Presenter{

    override fun loadNote(id: Long) {
        disposables.add(noteDetailUseCase.findNoteById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { view?.showLoading() }
                .doFinally { view?.hideLoading() }
                .doOnError(Timber::e)
                .subscribe({ view?.onLoadNoteSuccess(it) }, { view?.onLoadNoteError() }))
    }

    override fun deleteNote(id: Long) {
        disposables.add(deleteNoteUseCase.delete(Note(id))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(Timber::e)
                .subscribe({ view?.onDeleteNoteSuccess() }, { view?.onDeleteNoteError() }))
    }
}