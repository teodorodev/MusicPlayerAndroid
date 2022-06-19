package com.teodoro.musicplayer

import android.content.pm.PackageManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

import com.teodoro.musicplayer.adapters.ViewPagerAdapter
import com.teodoro.musicplayer.fragments.*
import com.teodoro.musicplayer.gambiarras.ListMusics
import com.teodoro.musicplayer.models.Song
import java.io.File

class MainActivity : AppCompatActivity() {

    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager

    lateinit var mediaPlayer: MediaPlayer

    var songList: ArrayList<Song> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPagear()
        verifyReadExternalStorageGaranted()
        getMusics()

    }


    private fun viewPagear(){
        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)
        tabLayout.setupWithViewPager(viewPager)

        var viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        // viewPagerAdapter.addFragment(AlbumsFragment(), "ALBUMS")
        //viewPagerAdapter.addFragment(ArtistsFragment(), "ARTISTS")
        // viewPagerAdapter.addFragment(FoldersFragment(), "FOLDERS")
        //viewPagerAdapter.addFragment(GenresFragment(), "GENRES")
        viewPagerAdapter.addFragment(SongsFragment(), "SONGS")
        viewPager.adapter = viewPagerAdapter
    }

    //Verifica se há permissão para ler arquivos externos
    private fun verifyReadExternalStorageGaranted(){
        if (!readExternalStorageGaranted()){
            requestReadExternalStorage()
        }
    }

    //Retorna se tem permissão para ler arquivos externos
    private fun readExternalStorageGaranted(): Boolean{
        return ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }

    //Solicita oermissão para ler arquivos externos
    private fun requestReadExternalStorage(){
            ActivityCompat.requestPermissions(
           this,
            arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
            MANAGE_EXTERNAL_STORAGE_CODE)
    }

    private fun getMusics(){
        var projection = arrayOf<String> (
            MediaStore.Audio.Media.DATA,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.ALBUM,
            MediaStore.Audio.Media.ARTIST
        )

        var selection = MediaStore.Audio.Media.IS_MUSIC + " != 0"
        var cursor = contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, projection, selection, null, null)

        if (cursor != null) {
            while (cursor.moveToNext()){
                var songData = Song(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4)
                )

                if ( songData.folders != ( "WhatsApp Audio")){

                    if (File(songData.path).exists())
                        songList.add(songData)
                }




                //if (songList.size == 0)
                  //  Toast.makeText(this,"Nenhuma música encontrada", Toast.LENGTH_SHORT).show()
            }

            ListMusics.MUSICAS = songList
        }

    }

    companion object{
        private const val MANAGE_EXTERNAL_STORAGE_CODE = 1000
    }
}