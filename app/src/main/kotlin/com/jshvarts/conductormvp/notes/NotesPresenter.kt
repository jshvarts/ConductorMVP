package com.jshvarts.conductormvp.notes

import com.jshvarts.data.model.ModelMapper
import com.jshvarts.data.model.Note
import com.jshvarts.data.repository.NoteDao
import timber.log.Timber
import javax.inject.Inject

class NotesPresenter @Inject constructor(private val noteDao: NoteDao, private val modelMapper: ModelMapper) : NotesContract.Presenter {

    private lateinit var view: NotesView

    override fun detachView() {
        Timber.d("NotesPresenter::detachView")
    }

    override fun attachView(view: NotesView) {
        Timber.d("NotesPresenter::attachView")
        this.view = view
    }

    override fun loadNotes(): List<Note> = noteDao.getAllNotes().map { it -> modelMapper.fromEntity(it) }
}