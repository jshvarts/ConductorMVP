package com.jshvarts.conductormvp.notes

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import butterknife.BindView
import butterknife.OnClick
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler
import com.jshvarts.conductormvp.R
import com.jshvarts.conductormvp.app.NotesApp
import com.jshvarts.conductormvp.model.Note
import com.jshvarts.conductormvp.mvp.BaseView
import com.jshvarts.conductormvp.notedetail.NoteDetailView
import javax.inject.Inject

class NotesView : BaseView(), NotesContract.View {

    @Inject
    lateinit var presenter: NotesPresenter

    @BindView(R.id.recycler_view)
    lateinit var recyclerView: RecyclerView

    private lateinit var recyclerViewAdapter: NotesAdapter

    override fun onAttach(view: View) {

        DaggerNotesComponent.builder()
                .appComponent(NotesApp.component)
                .notesModule(NotesModule())
                .build()
                .inject(this)

        initRecyclerView(view.context)

        presenter.attachView(this)
        presenter.loadNotes()
    }

    override fun onDetach(view: View) {
        super.onDetach(view)
        presenter.detachView()
    }

    override fun displayNotes(notes: List<Note>) {
        recyclerViewAdapter.updateNotes(notes)
    }

    override fun onUnableToLoadNotes() {
        Toast.makeText(this.applicationContext, R.string.notes_load_error, Toast.LENGTH_LONG).show()
    }

    override fun getLayoutId() = R.layout.notes_view

    private fun initRecyclerView(context: Context) {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerViewAdapter = NotesAdapter()
        recyclerViewAdapter.onItemClick = { onNoteClicked(it)}
        recyclerView.adapter = recyclerViewAdapter
    }

    private fun onNoteClicked(note: Note) {
        val noteDetailView = NoteDetailView().apply {
            args.putLong(NoteDetailView.EXTRA_NOTE_ID, note.id)
        }
        router.pushController(RouterTransaction.with(noteDetailView)
                .pushChangeHandler(FadeChangeHandler())
                .popChangeHandler(FadeChangeHandler()))
    }

    @OnClick(R.id.fab)
    override fun onFabClicked() {
        Toast.makeText(this.applicationContext, "show add Note view", Toast.LENGTH_LONG).show()
    }
}