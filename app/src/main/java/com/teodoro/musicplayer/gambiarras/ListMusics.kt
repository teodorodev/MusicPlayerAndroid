package com.teodoro.musicplayer.gambiarras

import com.teodoro.musicplayer.adapters.recyclerView.SongViewHolder
import com.teodoro.musicplayer.models.Song

class ListMusics {
    companion object{
        var MUSICAS: ArrayList<Song> = ArrayList()

        fun listFolders(): ArrayList<String>{
            var folders: ArrayList<String> = ArrayList()

            for (a in MUSICAS){

                if (!folders.contains(a.folders))
                    folders.add(a.folders)
            }

            return folders
        }

        lateinit var LASTSONGVIEWHOLDER: SongViewHolder
    }
}