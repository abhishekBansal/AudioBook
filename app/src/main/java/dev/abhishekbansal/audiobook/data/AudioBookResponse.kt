package dev.abhishekbansal.audiobook.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AudioBookResponse (

    val resultCount : Int,
    val results : List<AudioBook>
)