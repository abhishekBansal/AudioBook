package dev.abhishekbansal.audiobook.list.repository

import dev.abhishekbansal.audiobook.data.AudioBook

interface AudioBookDataSource {
    suspend fun getAudioBooks(): List<AudioBook>
}