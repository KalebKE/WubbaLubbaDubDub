package com.kalebkircher.wubbalubbadubdub.ui.navigation

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kalebkircher.wubbalubbadubdub.ui.screen.CharacterProfileScreen
import com.kalebkircher.wubbalubbadubdub.ui.screen.CharactersScreen
import kotlinx.serialization.Serializable

@Serializable
data class SelectedCharacter(val id: Long)

@Serializable
object Characters

@SuppressLint("NotConstructor")
@Composable
fun NavigationGraph() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Characters) {
        composable<Characters> {
            CharactersScreen().CharactersGrid(onNavigateToCharacterProfileScreen = { character: SelectedCharacter -> navController.navigate(character) })
        }
        composable<SelectedCharacter> {
            CharacterProfileScreen().CharacterProfile()
        }
    }
}
