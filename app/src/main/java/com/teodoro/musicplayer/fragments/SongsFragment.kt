package com.teodoro.musicplayer.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.teodoro.musicplayer.MainActivity
import com.teodoro.musicplayer.R
import com.teodoro.musicplayer.adapters.recyclerView.SongAdapter
import com.teodoro.musicplayer.gambiarras.ListMusics
import com.teodoro.musicplayer.models.Audio

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

private const val ARG_PARAM3 = "param3"



/**
 * A simple [Fragment] subclass.
 * Use the [SongsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SongsFragment() : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var fragmentSongs: View? = null

    //Arraylist para receber o a lista de musicas do MainActivity
    private var songs: ArrayList<Audio> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentSongs = inflater.inflate(R.layout.fragment_songs, container, false)
        listners()
        return fragmentSongs
    }

    private fun listners() {
        val adapter = SongAdapter(requireContext(), ListMusics.MUSICAS)
        val recycler = fragmentSongs?.findViewById<RecyclerView>(R.id.recyclerSongsFrag)
        recycler?.layoutManager = LinearLayoutManager(requireContext())
        recycler?.itemAnimator = DefaultItemAnimator()
        recycler?.adapter =adapter
    }

    companion object {

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SongsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SongsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)



                }

            }
    }
}