package com.jshvarts.conductormvp.notes

import com.jshvarts.conductormvp.domain.NoteRepository
import timber.log.Timber
import javax.inject.Inject

class NotesPresenter @Inject constructor(private val repository: NoteRepository) : NotesContract.Presenter {

    private lateinit var view: NotesView

    override fun detachView() {
        Timber.d("NotesPresenter::detachView")
    }

    override fun attachView(view: NotesView) {
        Timber.d("NotesPresenter::attachView")
        this.view = view
    }

    override fun loadNotes() = repository.getAllNotes()
}