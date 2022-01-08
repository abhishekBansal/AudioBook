package dev.abhishekbansal.audiobook.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.abhishekbansal.audiobook.list.repository.AudioBookRepository

class AudioBookViewModel(private val repository: AudioBookRepository): ViewModel() {
    private val uiState = MutableLiveData<AudioBookUiState>()
    fun uiState() = uiState as LiveData<AudioBookUiState>

    
}