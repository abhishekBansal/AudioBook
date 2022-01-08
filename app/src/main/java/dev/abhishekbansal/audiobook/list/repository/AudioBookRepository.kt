package dev.abhishekbansal.audiobook.list.repository

import dev.abhishekbansal.audiobook.data.AudioBook

class AudioBookRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : AudioBookDataSource {
    override suspend fun getAudioBooks(): List<AudioBook> {
        return remoteDataSource.getAudioBooks()
    }
}