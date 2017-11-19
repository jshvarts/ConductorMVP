package com.jshvarts.conductormvp.addnote

import com.jshvarts.conductormvp.domain.NoteRepository
import com.jshvarts.conductormvp.model.Note
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject

class AddNotePresenter @Inject constructor(private val repository: NoteRepository) : AddNoteContract.Presenter {

    private lateinit var view: AddNoteView

    private val disposables: CompositeDisposable = CompositeDisposable()

    override fun detachView() {
        Timber.d("NotesPresenter::detachView")
        disposables.clear()
    }

    override fun attachView(view: AddNoteView) {
        Timber.d("NotesPresenter::attachView")
        this.view = view
    }

    override fun addNote(noteText: String) {
        val note = Note(0, noteText)
        if (!note.isValid()) {
            view.onNoteValidationFailed()
            return
        }
        repository.add(note)
        view.onNoteAddSuccess()
    }
}