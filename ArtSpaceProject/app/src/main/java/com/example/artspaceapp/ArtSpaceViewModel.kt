package com.example.artspaceapp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class ArtSpaceUiState(
    val currentIndex: Int = 0
)

class ArtSpaceViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ArtSpaceUiState())
    val uiState: StateFlow<ArtSpaceUiState> = _uiState.asStateFlow()

    fun nextImage() {
        if (_uiState.value.currentIndex < 2) {
            _uiState.update { currentState ->
                currentState.copy(currentIndex = currentState.currentIndex + 1)
            }
        }
    }

    fun previousImage() {
        if (_uiState.value.currentIndex > 0) {
            _uiState.update { currentState ->
                currentState.copy(currentIndex = currentState.currentIndex - 1)
            }
        }
    }
}