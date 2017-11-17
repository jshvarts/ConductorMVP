package com.jshvarts.conductormvp.notedetail

import com.jshvarts.conductormvp.domain.NoteRepository
import timber.log.Timber
import javax.inject.Inject

class NoteDetailPresenter @Inject constructor(private val repository: NoteRepository) : NoteDetailContract.Presenter{

    private lateinit var view: NoteDetailView

    override fun loadNote(id: Long) {
        view.displayNote(repository.findNoteById(id))
    }

    override fun detachView() {
        Timber.d("NoteDetailPresenter::detachView")
    }

    override fun attachView(view: NoteDetailView) {
        Timber.d("NoteDetailPresenter::attachView")
        this.view = view
    }
}