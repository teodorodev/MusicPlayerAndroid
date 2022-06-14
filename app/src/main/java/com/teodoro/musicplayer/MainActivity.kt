package com.teodoro.musicplayer

import android.content.pm.PackageManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

import com.teodoro.musicplayer.adapters.ViewPagerAdapter
import com.teodoro.musicplayer.fragments.*

class MainActivity : AppCompatActivity() {

    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager

    lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPagear()
        verifyReadExternalStorageGaranted()

        var projection = arrayOf<String> (
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.DATA,
            MediaStore.Audio.Media.DURATION
        )


        var selection = MediaStore.Audio.Media.IS_MUSIC + " != 0"
        var cursor = contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, projection, selection, null, null)


        //Botão de teste/////////////////////////////////////////////////////////////////////////////////////
      findViewById<Button>(R.id.buttonTest1).setOnClickListener {
           mediaPlayer = MediaPlayer.create(this, R.raw.aguas)
          mediaPlayer?.start() // no need to call prepare(); create() does that for you
      }

        findViewById<Button>(R.id.buttonTest2).setOnClickListener {
            println("stop")
           println(            MediaStore.Audio.Media.TITLE)// no need to call prepare(); create() does that for you
        }
    }


    private fun viewPagear(){
        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)
        tabLayout.setupWithViewPager(viewPager)

        var viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)

        viewPagerAdapter.addFragment(AlbumsFragment(), "ALBUMS")
        viewPagerAdapter.addFragment(ArtistsFragment(), "ARTISTS")
        viewPagerAdapter.addFragment(FoldersFragment(), "FOLDERS")
        viewPagerAdapter.addFragment(GenresFragment(), "GENRES")
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

    companion object{
        private const val MANAGE_EXTERNAL_STORAGE_CODE = 1000
    }
}