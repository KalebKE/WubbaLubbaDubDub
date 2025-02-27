package com.kalebkircher.wubbalubbadubdub.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.kalebkircher.wubbalubbadubdub.data.repository.CharacterRepository
import com.kalebkircher.wubbalubbadubdub.ui.mapper.CharacterMapper
import com.kalebkircher.wubbalubbadubdub.ui.navigation.SelectedCharacter
import com.kalebkircher.wubbalubbadubdub.ui.state.CharacterState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterProfileViewModel @Inject constructor(savedStateHandle: SavedStateHandle, private val repository: CharacterRepository) : ViewModel() {

    private val selectedCharacter = savedStateHandle.toRoute<SelectedCharacter>()

    // Backing property to avoid state updates from other classes
    private val _uiState: MutableStateFlow<CharacterProfileUiState?> = MutableStateFlow(null)

    // The UI collects from this StateFlow to get its state updates
    val uiState: StateFlow<CharacterProfileUiState?> = _uiState

    init {
        getCharacterById(selectedCharacter.id)
    }

    private fun getCharacterById(id: Long) {
        viewModelScope.launch {
            _uiState.value = CharacterProfileUiState(CharacterMapper.map(repository.getCharactersById(id)))
        }
    }

    data class CharacterProfileUiState(val character: CharacterState)
}

