package com.teodoro.musicplayer.adapters.recyclerView

import android.content.Context
import android.media.AudioManager
import android.media.Image
import android.media.MediaPlayer
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.drawable.toDrawable
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.teodoro.musicplayer.R
import com.teodoro.musicplayer.gambiarras.ListMusics
import com.teodoro.musicplayer.models.Song

class SongAdapter(val context: Context, val items: ArrayList<Song>): RecyclerView.Adapter<SongViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.line_songs, parent, false)
        val holder = SongViewHolder(view)
        return  holder
    }

    var mediaPlayer: MediaPlayer? = null
    var myUri: Uri? = null
    var btnImagePlayPause = R.drawable.ic_baseline_play_arrow_24

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = items[position]
        holder.tvMusicName.text = song.title
        holder.tvMusicArtist.text = song.artist
        holder.btnPlayPause.setImageResource(btnImagePlayPause)
        holder.tvMusicName.setOnClickListener{

            playPause(holder, song)


        }
    }

    override fun getItemCount(): Int {
        return  items.size
    }

    private fun playPause(holder: SongViewHolder, song: Song) {

        alterBtnImagePlayPause(holder)


        //Se estiver reproduzindo
        if (mediaPlayer?.isPlaying == true ){

            //Se for passada outra música
            if (song.path != myUri?.path){

                playNewMusic(song)

                //Baguncinha
                ListMusics.LASTSONGVIEWHOLDER.btnPlayPause.setImageResource(R.drawable.ic_baseline_play_arrow_24)
                ListMusics.LASTSONGVIEWHOLDER = holder
                alterBtnImagePlayPause(holder)
            }else{//Se for passada a mesma música
                mediaPlayer?.pause()
            }



        }else {//Se não tiver reproduzindo
            //Se  for passada a mesma música
            if (song.path == myUri?.path){
                    mediaPlayer?.start()
                } else {//Se for passada outa música
                    playNewMusic(song)
                ListMusics.LASTSONGVIEWHOLDER = holder
                }
        }


    }

    private fun alterBtnImagePlayPause(holder: SongViewHolder) {


        if (btnImagePlayPause == R.drawable.ic_baseline_play_arrow_24){
            btnImagePlayPause = R.drawable.ic_baseline_pause_24
        } else{
            btnImagePlayPause = R.drawable.ic_baseline_play_arrow_24
        }
        holder.btnPlayPause.setImageResource(btnImagePlayPause)
    }

    fun playNewMusic(song: Song){
        mediaPlayer?.release()
        mediaPlayer = null
        myUri = song.path.toUri() // initialize Uri here
        mediaPlayer = MediaPlayer().apply {
            AudioManager.STREAM_MUSIC
            setDataSource(context, myUri!!)
            prepare()
            start()
            pause()
        }
        mediaPlayer?.start()
    }




}