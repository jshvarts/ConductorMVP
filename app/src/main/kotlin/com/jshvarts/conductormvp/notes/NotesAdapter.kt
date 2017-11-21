package com.jshvarts.conductormvp.notes

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.jshvarts.conductormvp.R
import com.jshvarts.notedomain.model.Note

/**
 * Recycler View Adapter for displaying and selecting notes.
 */
class NotesAdapter : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    val notes = mutableListOf<Note>()

    var onItemClick: (Note) -> Unit = {}

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        notes[position].apply { holder.noteText.text = this.noteText }
    }

    override fun getItemCount() = notes.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val noteText = LayoutInflater.from(parent.context)
                .inflate(R.layout.note_item, parent, false) as TextView
        val viewHolder = ViewHolder(noteText)
        noteText.setOnClickListener { onItemClick(notes[viewHolder.adapterPosition]) }
        return viewHolder
    }

    fun updateNotes(notes: List<Note>) {
        this.notes.clear()
        this.notes.addAll(notes)
        notifyDataSetChanged()
    }

    class ViewHolder(val noteText: TextView) : RecyclerView.ViewHolder(noteText)
}