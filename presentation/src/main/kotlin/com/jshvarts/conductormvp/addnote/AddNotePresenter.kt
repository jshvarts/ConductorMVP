package com.jshvarts.conductormvp.addnote

import com.jshvarts.conductormvp.mvp.BasePresenter
import com.jshvarts.notedomain.model.Note
import com.jshvarts.notedomain.usecases.AddNoteUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AddNotePresenter @Inject constructor(private val addNoteUseCase: AddNoteUseCase) : BasePresenter<AddNoteView>(), AddNoteContract.Presenter {

    override fun addNote(noteText: String) {
        disposables.add(addNoteUseCase.add(Note(noteText = noteText))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ view?.onAddNoteSuccess() }, this::onAddNoteError))
    }

    private fun onAddNoteError(throwable: Throwable) {
        when(throwable) {
            is IllegalArgumentException -> view?.onNoteValidationFailed()
            else -> view?.onAddNoteError(throwable)
        }
    }
}