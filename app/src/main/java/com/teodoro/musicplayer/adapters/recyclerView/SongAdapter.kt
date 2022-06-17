package com.teodoro.musicplayer.adapters.recyclerView

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.teodoro.musicplayer.R
import com.teodoro.musicplayer.models.Audio

class SongAdapter(val context: Context, val items: ArrayList<Audio>): RecyclerView.Adapter<SongViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.line_songs, parent, false)
        val holder = SongViewHolder(view)
        return  holder
    }

    var mediaPlayer: MediaPlayer? = null

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = items[position]
        holder.tvMusicName.text = song.title
        holder.tvMusicName.setOnClickListener{

            if (mediaPlayer?.isPlaying == true){
                mediaPlayer?.release()
                mediaPlayer = null
            }
            val myUri: Uri = song.path.toUri() // initialize Uri here
             mediaPlayer = MediaPlayer().apply {
                setAudioStreamType(AudioManager.STREAM_MUSIC)
                setDataSource(context, myUri)
                prepare()
                start()
            }
            mediaPlayer?.start()

        }
    }

    override fun getItemCount(): Int {
        return  items.size
    }
}