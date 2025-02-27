package com.kalebkircher.wubbalubbadubdub

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kalebkircher.wubbalubbadubdub.ui.screen.CharacterProfileScreen
import com.kalebkircher.wubbalubbadubdub.ui.state.CharacterState
import com.kalebkircher.wubbalubbadubdub.ui.state.LocationState
import com.kalebkircher.wubbalubbadubdub.ui.state.OriginState
import com.kalebkircher.wubbalubbadubdub.ui.theme.AppTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CharacterProfileScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun characterProfileScreenDisplaysCharacterDetails() {
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

        composeTestRule.setContent {
            AppTheme {
                CharacterProfileScreen().CharacterProfile(characterState)
            }
        }

        composeTestRule.onNodeWithText("Rick Sanchez").assertIsDisplayed()
        composeTestRule.onNodeWithText("Status: Alive").assertIsDisplayed()
        composeTestRule.onNodeWithText("Species: Human").assertIsDisplayed()
        composeTestRule.onNodeWithText("Origin: Earth").assertIsDisplayed()
    }
}