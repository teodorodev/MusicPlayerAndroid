package com.teodoro.musicplayer.adapters.recyclerView

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.teodoro.musicplayer.R

class FolderViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val tvFolderName = itemView.findViewById<TextView>(R.id.tvFolderName)
}