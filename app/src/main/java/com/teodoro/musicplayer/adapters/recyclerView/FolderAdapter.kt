package com.teodoro.musicplayer.adapters.recyclerView

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.teodoro.musicplayer.R
import java.util.zip.Inflater

class FolderAdapter(var context: Context, var items: ArrayList<String>): RecyclerView.Adapter<FolderViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FolderViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.line_folder, parent, false)
        return FolderViewHolder(view)
    }

    override fun onBindViewHolder(holder: FolderViewHolder, position: Int) {
        holder.tvFolderName.text = items.get(position)
    }

    override fun getItemCount(): Int {
       return items.size
    }
}