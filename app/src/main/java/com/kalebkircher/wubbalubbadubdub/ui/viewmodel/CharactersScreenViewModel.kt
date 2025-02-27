package com.kalebkircher.wubbalubbadubdub.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.kalebkircher.wubbalubbadubdub.data.repository.CharacterRepository
import com.kalebkircher.wubbalubbadubdub.ui.components.SelectionGridItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class CharactersScreenViewModel @Inject constructor(private val repository: CharacterRepository) : ViewModel() {
    // Backing property to avoid state updates from other classes
    private val _queryState: MutableStateFlow<String> = MutableStateFlow("")

    fun searchCharactersByName(name: String) {
        _queryState.value = name
    }

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val uiState = _queryState.debounce(250).flatMapLatest { repository.getCharactersByNameFlow(it).map { pagingData -> pagingData.map { character -> SelectionGridItem(character.id, character.name, character.image) } }.cachedIn(viewModelScope)  }
}

