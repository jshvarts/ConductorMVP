package com.jshvarts.conductormvp.colors

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.jshvarts.conductormvp.R


/**
 * Recycler View Adapter for displaying and selecting colors.
 */
class ColorsAdapter(private val colors: List<String>) : RecyclerView.Adapter<ColorsAdapter.ViewHolder>() {

    var onItemClick: (Int) -> Unit = {}

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.colorName.text = colors[position]
    }

    override fun getItemCount() = colors.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val colorName = LayoutInflater.from(parent.context)
                .inflate(R.layout.color_item, parent, false) as TextView
        val viewHolder = ViewHolder(colorName)
        colorName.setOnClickListener { onItemClick(viewHolder.adapterPosition) }
        return viewHolder
    }

    class ViewHolder(val colorName: TextView) : RecyclerView.ViewHolder(colorName)
}