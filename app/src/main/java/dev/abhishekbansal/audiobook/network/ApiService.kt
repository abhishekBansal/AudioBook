package dev.abhishekbansal.audiobook.network

import dev.abhishekbansal.audiobook.data.AudioBook
import dev.abhishekbansal.audiobook.data.AudioBookResponse
import retrofit2.http.GET

interface ApiService {

    @GET("/v3/2abb5b4e-b46b-4b0d-a7ba-a20eb394782a")
    suspend fun getAudioBooks(): AudioBookResponse
}