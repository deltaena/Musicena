package com.deltaena.common.pojos

class Track {
    var id: Long = 0
    lateinit var title: String
    lateinit var link: String
    var duration: Long = 0

    lateinit var artist: Artist
    lateinit var album: Album
}