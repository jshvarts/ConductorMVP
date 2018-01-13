package com.jshvarts.conductormvp.notes

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import butterknife.BindView
import butterknife.OnClick
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler
import com.jshvarts.conductormvp.NotesApp
import com.jshvarts.conductormvp.R
import com.jshvarts.conductormvp.addnote.AddNoteView
import com.jshvarts.conductormvp.viewext.initRecyclerView
import com.jshvarts.conductormvp.mvp.BaseView
import com.jshvarts.conductormvp.mvp.MvpPresenter
import com.jshvarts.conductormvp.notedetail.NoteDetailView
import com.jshvarts.notedomain.model.Note
import timber.log.Timber
import javax.inject.Inject

class NotesView : BaseView(), NotesContract.View {

    @Inject
    lateinit var presenter: NotesPresenter

    @BindView(R.id.recycler_view)
    lateinit var recyclerView: RecyclerView

    private val clickListener: (Note) -> Unit = { onNoteClicked(it) }

    private val recyclerViewAdapter: NotesAdapter = NotesAdapter(clickListener)

    override fun injectDependencies() {
        DaggerNotesComponent.builder()
                .appComponent(NotesApp.component)
                .notesModule(NotesModule())
                .build()
                .inject(this)
    }

    override fun onAttach(view: View) {
        super.onAttach(view)
        recyclerView.initRecyclerView(LinearLayoutManager(view.context), recyclerViewAdapter)
        with(presenter) {
            presenter.start(this@NotesView)
            presenter.loadNotes()
        }
    }

    override fun onLoadNotesSuccess(notes: List<Note>) {
        recyclerViewAdapter.updateNotes(notes)
    }

    override fun onLoadNotesError(throwable: Throwable) {
        Timber.e(throwable)
        showMessage(R.string.notes_load_error)
    }

    private fun onNoteClicked(note: Note) {
        val noteDetailView = NoteDetailView().apply {
            args.putLong(KEY_NOTE_ID, note.id)
        }
        router.pushController(RouterTransaction.with(noteDetailView)
                .pushChangeHandler(FadeChangeHandler())
                .popChangeHandler(FadeChangeHandler()))
    }

    @OnClick(R.id.fab)
    override fun onFabClicked() {
        router.pushController(RouterTransaction.with(AddNoteView())
                .pushChangeHandler(FadeChangeHandler())
                .popChangeHandler(FadeChangeHandler()))
    }

    override fun getLayoutId() = R.layout.notes_view

    override fun getToolbarTitleId() = R.string.screen_title_notes

    override fun getPresenter(): MvpPresenter = presenter
}