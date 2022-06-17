package com.teodoro.musicplayer.gambiarras

import com.teodoro.musicplayer.models.Audio

class ListMusics {
    companion object{
        var MUSICAS: ArrayList<Audio> = ArrayList()

        fun listFolders(): ArrayList<String>{
            var folders: ArrayList<String> = ArrayList()

            for (a in MUSICAS){

                if (!folders.contains(a.folders))
                    folders.add(a.folders)
            }

            return folders
        }
    }
}