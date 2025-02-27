package com.kalebkircher.wubbalubbadubdub.ui.screen

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.map
import com.kalebkircher.wubbalubbadubdub.R
import com.kalebkircher.wubbalubbadubdub.data.entity.CharacterEntity
import com.kalebkircher.wubbalubbadubdub.data.entity.LocationEntity
import com.kalebkircher.wubbalubbadubdub.data.entity.OriginEntity
import com.kalebkircher.wubbalubbadubdub.ui.components.AppBar
import com.kalebkircher.wubbalubbadubdub.ui.components.PagingSelectionGrid
import com.kalebkircher.wubbalubbadubdub.ui.components.SelectionGridItem
import com.kalebkircher.wubbalubbadubdub.ui.navigation.SelectedCharacter
import com.kalebkircher.wubbalubbadubdub.ui.theme.AppTheme
import com.kalebkircher.wubbalubbadubdub.ui.viewmodel.CharactersScreenViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

class CharactersScreen {
    private val pagingSelectionGrid = PagingSelectionGrid(R.string.label_loading, R.string.label_error, R.drawable.rick_and_morty)
    private val appBar = AppBar()

    @Composable
    fun CharactersGrid(viewModel: CharactersScreenViewModel = hiltViewModel<CharactersScreenViewModel>(), onNavigateToCharacterProfileScreen: (character: SelectedCharacter) -> Unit) {
        val characters = viewModel.uiState.collectAsLazyPagingItems()

        Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
            appBar.AppBar()
        }) { innerPadding ->
            CharacterGrid(characters, {
                viewModel.searchCharactersByName(it)
            }, {
                onNavigateToCharacterProfileScreen(SelectedCharacter(id = it))
            }, Modifier.padding(innerPadding))
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun CharacterGrid(items: LazyPagingItems<SelectionGridItem>, onQueryChange: (String) -> Unit = {}, onSelected: (Long) -> Unit = {}, modifier: Modifier = Modifier) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            var searchQuery by remember { mutableStateOf("") }
            var expanded by remember { mutableStateOf(false) }

            SearchBar(modifier = Modifier
                .padding(8.dp, 0.dp, 8.dp, 0.dp)
                .fillMaxWidth(), windowInsets = WindowInsets(top = 0.dp), inputField = {
                SearchBarDefaults.InputField(
                    query = searchQuery,
                    onQueryChange = {
                        searchQuery = it
                        onQueryChange.invoke(it)
                    },
                    onSearch = { expanded = false },
                    expanded = expanded,
                    onExpandedChange = { expanded = false },
                    placeholder = { Text("Search Characters") },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search Icon") },
                )
            }, expanded = expanded, onExpandedChange = { }) {}

            pagingSelectionGrid.SelectionItemLazyVerticalGrid(items, onSelected)
        }
    }

    @Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, showSystemUi = true, showBackground = true, name = "light")
    @Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showSystemUi = true, showBackground = true, name = "dark")
    @Composable
    private fun LazyGridPreview() {

        val characterState = CharacterEntity(
            id = 1L,
            name = "Rick Sanchez",
            status = "Alive",
            species = "Human",
            type = "",
            gender = "Male",
            origin = OriginEntity(name = "Earth", url = ""),
            location = LocationEntity(name = "Earth", url = ""),
            image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            url = "https://rickandmortyapi.com/api/character/1",
            created = "2017-11-04T18:48:46.250Z"
        )

        val characters = listOf(characterState)
        // pass pagingData containing fake data to a MutableStateFlow
        val fakeDataFlow = MutableStateFlow(PagingData.from(characters))

        val items = fakeDataFlow.map { pagingData -> pagingData.map { character -> SelectionGridItem(character.id, character.name, character.image) } }.collectAsLazyPagingItems()

        AppTheme {
            Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
                AppBar()
            }) { innerPadding ->
                CharacterGrid(items, modifier = Modifier.padding(innerPadding))
            }
        }
    }
}