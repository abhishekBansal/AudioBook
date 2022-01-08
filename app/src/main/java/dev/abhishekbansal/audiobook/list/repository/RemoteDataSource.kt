package dev.abhishekbansal.audiobook.list.repository

import dev.abhishekbansal.audiobook.data.AudioBook
import dev.abhishekbansal.audiobook.network.ApiService

class RemoteDataSource(private val apiService: ApiService): AudioBookDataSource {
    override suspend fun getAudioBooks(): List<AudioBook> {
        TODO("Not yet implemented")
    }
}