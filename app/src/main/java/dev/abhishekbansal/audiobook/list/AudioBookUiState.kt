package dev.abhishekbansal.audiobook.list

import dev.abhishekbansal.audiobook.data.AudioBook

sealed class AudioBookUiState

object LoadingState: AudioBookUiState()
class SuccessState(val bookList: List<AdapterData>): AudioBookUiState()
class ErrorState(val messageRes: Int): AudioBookUiState()