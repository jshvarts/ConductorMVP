package com.jshvarts.conductormvp.notes

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import butterknife.BindView
import com.bluelinelabs.conductor.RouterTransaction
import com.jshvarts.conductormvp.R
import com.jshvarts.conductormvp.app.NotesApp
import com.jshvarts.conductormvp.notedetail.NoteDetailView
import com.jshvarts.conductormvp.mvp.BaseView
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

        recyclerView.layoutManager = LinearLayoutManager(view.context)

        presenter.attachView(this)
        val notes = presenter.loadNotes()

        recyclerViewAdapter = NotesAdapter(notes)
        recyclerViewAdapter.onItemClick = { onItemClicked(notes[it].noteText)}
        recyclerView.adapter = recyclerViewAdapter
    }

    override fun onDetach(view: View) {
        super.onDetach(view)
        presenter.detachView()
    }
    override fun onItemClicked(note: String) {
        val noteDetailView = NoteDetailView().apply {
            args.putString(NoteDetailView.EXTRA_ITEM, note)
        }
        router.pushController(RouterTransaction.with(noteDetailView))
    }

    override fun onUnableToLoadItems() {
        TODO("not implemented")
    }

    override fun getLayoutId() = R.layout.notes_recycler_view
}