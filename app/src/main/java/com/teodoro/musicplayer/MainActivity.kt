package com.teodoro.musicplayer

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

import com.teodoro.musicplayer.adapters.ViewPagerAdapter
import com.teodoro.musicplayer.fragments.*
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPagear()
        verifyReadExternalStorageGaranted()


        //Botão de teste
      findViewById<Button>(R.id.buttonTest).setOnClickListener {
        verifyReadExternalStorageGaranted()
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
        if (ReadExternalStorageGaranted()){
            println("GARANTIDO")
        }else{
            println("NÃO GARANTIDO")
            requestReadExternalStorage()
        }
    }

    //Retorna se tem permissão para ler arquivos externos
    private fun ReadExternalStorageGaranted(): Boolean{
        return ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }

    //Solicita oermissão para ler arquivos externos
    private fun requestReadExternalStorage(){
        ActivityCompat.requestPermissions(
            this,
           arrayOf(  android.Manifest.permission.READ_EXTERNAL_STORAGE),
            MANAGE_EXTERNAL_STORAGE_CODE
        )

        println("Solicitado")
    }

    companion object{
        private const val MANAGE_EXTERNAL_STORAGE_CODE = 1000
    }
}