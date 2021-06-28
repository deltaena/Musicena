package com.deltaena.common

import com.deltaena.common.pojos.Playlist
import com.deltaena.common.pojos.PlaylistList
import com.deltaena.common.pojos.Track
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DeezerService {

    @GET("playlist/5490610762&output=json")
    fun getLovedTracks(): Call<Playlist>

    @GET("user/{userId}/playlists")
    fun getPlaylists(@Path("userId") userId: String): Call<PlaylistList>
}