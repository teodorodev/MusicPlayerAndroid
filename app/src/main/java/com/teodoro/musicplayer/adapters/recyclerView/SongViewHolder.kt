package com.teodoro.musicplayer.adapters.recyclerView

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.teodoro.musicplayer.R

class SongViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
    val tvMusicName: TextView = itemView.findViewById(R.id.tvMusicName)
    val tvMusicArtist: TextView = itemView.findViewById(R.id.tvMusicArtist)
    val btnPlayPause: ImageView = itemView.findViewById(R.id.btnPlayPause)
}