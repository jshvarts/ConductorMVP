package com.jshvarts.conductormvp.notedetail

import android.view.View
import android.widget.TextView
import butterknife.BindView
import com.jshvarts.conductormvp.R
import com.jshvarts.conductormvp.mvp.BaseView

class NoteDetailView : BaseView() {

    companion object {
        const val EXTRA_ITEM = "NoteDetailView.item"
    }

    @BindView(R.id.note_text)
    lateinit var noteText: TextView

    override fun getLayoutId() = R.layout.note_detail

    override fun onAttach(view: View) {
        noteText.text = args.get(EXTRA_ITEM).toString()
    }
}