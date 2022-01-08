package dev.abhishekbansal.audiobook.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.abhishekbansal.audiobook.R
import dev.abhishekbansal.audiobook.data.AudioBook
import dev.abhishekbansal.audiobook.list.repository.AudioBookRepository
import kotlinx.coroutines.launch

class AudioBookViewModel(private val repository: AudioBookRepository) : ViewModel() {
    private val uiState = MutableLiveData<AudioBookUiState>()
    private var books = mutableListOf<AudioBook>()
    fun uiState() = uiState as LiveData<AudioBookUiState>

    init {
        uiState.value = LoadingState
    }

    fun getBooks() {
        uiState.value = LoadingState
        viewModelScope.launch {
            try {
                books.clear()
                books.addAll(repository.getAudioBooks())
                val adapterData = getGroupedData(Grouping.ARTIST)

                uiState.value = SuccessState(bookList = adapterData)
            } catch (error: Exception) {
                Log.w("Error", error)
                uiState.value = ErrorState()
            }
        }
    }

    private fun getGroupedData(grouping: Grouping): MutableList<AdapterData> {
        val adapterData = mutableListOf<AdapterData>()
        val groupedData = when (grouping) {
            Grouping.ARTIST -> books.groupBy { it.artistName }
            Grouping.GENRE -> books.groupBy { it.primaryGenreName }
        }
        groupedData.forEach { entry ->
            adapterData.add(Header(name = entry.key, books = entry.value))
        }
        return adapterData
    }

    fun groupItems(id: Int) {
        val newGrouping = if (id == R.id.menu_genre) {
            Grouping.GENRE
        } else {
            Grouping.ARTIST
        }

        (uiState().value as? SuccessState)?.apply {
            if (newGrouping != grouping) {
                uiState.value = SuccessState(getGroupedData(newGrouping), newGrouping)
            }
        }
    }
}