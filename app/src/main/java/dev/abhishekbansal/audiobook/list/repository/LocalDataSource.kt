package dev.abhishekbansal.audiobook.list.repository

import dev.abhishekbansal.audiobook.data.AudioBook

class LocalDataSource: AudioBookDataSource {
    override suspend fun getAudioBooks(): List<AudioBook> {
        TODO("Not yet implemented")
    }
}