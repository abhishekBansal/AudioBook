package dev.abhishekbansal.audiobook.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.abhishekbansal.audiobook.list.repository.AudioBookRepository
import kotlinx.coroutines.launch

class AudioBookViewModel(private val repository: AudioBookRepository): ViewModel() {
    private val uiState = MutableLiveData<AudioBookUiState>()
    fun uiState() = uiState as LiveData<AudioBookUiState>

    init {
        uiState.value = LoadingState
    }

    fun getBooks() {
        viewModelScope.launch {
            try {
                val books = repository.getAudioBooks()
                val adapterData = mutableListOf<AdapterData>()
                books.groupBy { it.artistName }
                    .forEach { entry ->
                        adapterData.add(Header(name = entry.key, books = entry.value))
                    }

                uiState.value = SuccessState(bookList = adapterData)
            } catch (error: Exception) {
                Log.w( "Error", error)
            }
        }
    }
}