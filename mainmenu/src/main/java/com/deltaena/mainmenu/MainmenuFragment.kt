package com.deltaena.mainmenu

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.deltaena.common.DeezerService
import com.deltaena.common.pojos.Playlist
import com.deltaena.common.pojos.PlaylistList
import com.deltaena.common.pojos.Track
import com.deltaena.mainmenu.databinding.FragmentMainmenuBinding
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainmenuFragment : Fragment() {

    private val TAG = "MainmenuFragment"

    lateinit var binding: FragmentMainmenuBinding

    val deezerService: DeezerService by lazy {
        val deezerServiceBaseUrl = resources.getString(R.string.deezerServiceBaseUrl)
        val retrofit = Retrofit.Builder().
            baseUrl(deezerServiceBaseUrl).
            addConverterFactory(GsonConverterFactory.create()).
            build()

        retrofit.create(DeezerService::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val call = deezerService.getPlaylists(resources.getString(R.string.userDeezerId))

        call.enqueue(object: Callback<PlaylistList>{
            override fun onResponse(call: Call<PlaylistList>, response: Response<PlaylistList>) {
                binding.textView.text = "fetched "+response.body()?.data?.size+" songs"
            }

            override fun onFailure(call: Call<PlaylistList>, t: Throwable) {
                Snackbar.make(requireView(), t.message.toString(), Snackbar.LENGTH_LONG).show()
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainmenuBinding.inflate(inflater)

        return binding.root
    }

}