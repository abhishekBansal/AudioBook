package dev.abhishekbansal.audiobook.list

sealed class AudioBookUiState

object LoadingState : AudioBookUiState()
class SuccessState(val bookList: List<AdapterData>, val grouping: Grouping = Grouping.ARTIST) : AudioBookUiState()
class ErrorState(val messageRes: Int? = null) : AudioBookUiState()

enum class Grouping {
    ARTIST,
    GENRE
}