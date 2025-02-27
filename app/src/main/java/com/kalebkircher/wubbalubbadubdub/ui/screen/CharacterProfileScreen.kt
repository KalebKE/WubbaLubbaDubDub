package com.kalebkircher.wubbalubbadubdub.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.kalebkircher.wubbalubbadubdub.R
import com.kalebkircher.wubbalubbadubdub.ui.components.AppBar
import com.kalebkircher.wubbalubbadubdub.ui.components.Loading
import com.kalebkircher.wubbalubbadubdub.ui.state.CharacterState
import com.kalebkircher.wubbalubbadubdub.ui.state.LocationState
import com.kalebkircher.wubbalubbadubdub.ui.state.OriginState
import com.kalebkircher.wubbalubbadubdub.ui.theme.AppTheme
import com.kalebkircher.wubbalubbadubdub.ui.viewmodel.CharacterProfileViewModel
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle


class CharacterProfileScreen {
    private val appBar = AppBar()

    @Composable
    fun CharacterProfile(viewModel: CharacterProfileViewModel = hiltViewModel<CharacterProfileViewModel>()) {
        Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
            appBar.AppBar()
        }) { innerPadding ->
            val state = viewModel.uiState.collectAsState()

            if (state.value != null) {
                CharacterProfile(state.value!!.character, modifier = Modifier.padding(innerPadding))
            } else {
                Loading(R.string.label_loading, modifier = Modifier.padding(innerPadding))
            }
        }
    }

    @Composable
    fun CharacterProfile(character: CharacterState, modifier: Modifier = Modifier) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) { // A card container for the profile
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp), verticalArrangement = Arrangement.SpaceBetween, horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(character.image)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.rick_and_morty),
                    contentDescription = stringResource(R.string.content_description_character_icon, character.name),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(CircleShape)
                        .width(250.dp)
                        .height(250.dp)
                )

                Text(modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp), text = character.name, style = MaterialTheme.typography.titleMedium)
            }

            Row(
                Modifier
                    .padding(16.dp)
                    .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround
            ) {
                Column(Modifier.weight(1F)) {
                    Text(stringResource(R.string.species_label, character.species), style = MaterialTheme.typography.titleMedium, textAlign = TextAlign.Start)
                    Text(stringResource(R.string.origin_label, character.origin.name), style = MaterialTheme.typography.titleMedium, textAlign = TextAlign.Start)
                    Text(stringResource(R.string.location_label, character.location.name), style = MaterialTheme.typography.titleMedium, textAlign = TextAlign.Start)
                }
                Column(Modifier.weight(1F)) {

                    val zdt = ZonedDateTime.parse(character.created)

                    Text(stringResource(R.string.status_label, character.status), style = MaterialTheme.typography.titleMedium, textAlign = TextAlign.Start)
                    if (character.type.isNotEmpty()) {
                        Text(stringResource(R.string.type_label, character.type), style = MaterialTheme.typography.titleMedium, textAlign = TextAlign.Start)
                    }
                    Text(stringResource(R.string.created_label, zdt.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM))), style = MaterialTheme.typography.titleMedium, textAlign = TextAlign.Start)
                }
            }
        }
    }

    @Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, showSystemUi = true, showBackground = true, name = "light")
    @Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showSystemUi = true, showBackground = true, name = "dark")
    @Composable
    private fun CharacterProfilePreview() {
        val characterState = CharacterState(
            id = 1L,
            name = "Rick Sanchez",
            status = "Alive",
            species = "Human",
            type = "",
            gender = "Male",
            origin = OriginState(name = "Earth", url = ""),
            location = LocationState(name = "Earth", url = ""),
            image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            url = "https://rickandmortyapi.com/api/character/1",
            created = "2017-11-04T18:48:46.250Z"
        )

        AppTheme {
            Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
                appBar.AppBar()
            }) { innerPadding ->
                CharacterProfile(characterState, modifier = Modifier.padding(innerPadding))
            }
        }
    }

}
