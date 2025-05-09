package com.example.character.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.character.data.Character

@Composable
fun MainScreen(viewModel: MainViewModel = viewModel()) {
    val nameInput by viewModel.nameInput
    val characters by viewModel.characters
    val selectedCharacter by viewModel.selectedCharacter
    val currentScreen by viewModel.currentScreen

    when (currentScreen) {
        Screen.Main -> {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    OutlinedTextField(
                        value = nameInput,
                        onValueChange = { viewModel.updateNameInput(it) },
                        label = { Text("Enter Character Name") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            if (nameInput.isNotBlank()) {
                                val newCharacter = Character(name = nameInput)
                                viewModel.createCharacter(newCharacter)
                            }
                        },
                        enabled = nameInput.isNotBlank()
                    ) {
                        Text("Create Character")
                    }
                }
            }
        }
        Screen.CharacterDetail -> {
            selectedCharacter?.let { char ->
                CharacterDetailScreen(
                    character = char,
                    onCharacterUpdated = { viewModel.updateCharacter(it) },
                    onNavigateToHome = { viewModel.navigateTo(Screen.CharacterDetail) },
                    onNavigateToCharacters = { viewModel.navigateTo(Screen.Characters) },
                    onNavigateToSkills = { viewModel.navigateTo(Screen.Skills) },
                    onNavigateToInventory = { viewModel.navigateTo(Screen.Inventory) },
                    onNavigateToWounds = { viewModel.navigateTo(Screen.Wounds) },
                    onNavigateToSettings = { viewModel.navigateTo(Screen.Settings) }
                )
            }
        }
        Screen.Characters -> {
            CharactersScreen(
                characters = characters,
                onNavigateToHome = { viewModel.navigateTo(Screen.CharacterDetail) },
                onNavigateToCharacters = { viewModel.navigateTo(Screen.Characters) },
                onNavigateToSkills = { viewModel.navigateTo(Screen.Skills) },
                onNavigateToInventory = { viewModel.navigateTo(Screen.Inventory) },
                onNavigateToWounds = { viewModel.navigateTo(Screen.Wounds) },
                onCharacterSelected = { viewModel.selectCharacter(it) },
                onCreateCharacter = { viewModel.createCharacter(it) }
            )
        }
        Screen.Skills -> {
            selectedCharacter?.let { char ->
                SkillsScreen(
                    character = char,
                    onSkillUpdated = { newSkills ->
                        viewModel.updateCharacter(char.copy(skills = newSkills))
                    },
                    onCharacterUpdated = { viewModel.updateCharacter(it) },
                    onNavigateToHome = { viewModel.navigateTo(Screen.CharacterDetail) },
                    onNavigateToCharacters = { viewModel.navigateTo(Screen.Characters) },
                    onNavigateToSkills = { viewModel.navigateTo(Screen.Skills) },
                    onNavigateToInventory = { viewModel.navigateTo(Screen.Inventory) },
                    onNavigateToWounds = { viewModel.navigateTo(Screen.Wounds) },
                    onNavigateToSettings = { viewModel.navigateTo(Screen.Settings) }
                )
            }
        }
        Screen.Inventory -> {
            selectedCharacter?.let { char ->
                InventoryScreen(
                    character = char,
                    onCharacterUpdated = { viewModel.updateCharacter(it) },
                    onNavigateToHome = { viewModel.navigateTo(Screen.CharacterDetail) },
                    onNavigateToCharacters = { viewModel.navigateTo(Screen.Characters) },
                    onNavigateToSkills = { viewModel.navigateTo(Screen.Skills) },
                    onNavigateToInventory = { viewModel.navigateTo(Screen.Inventory) },
                    onNavigateToWounds = { viewModel.navigateTo(Screen.Wounds) },
                    onNavigateToSettings = { viewModel.navigateTo(Screen.Settings) }
                )
            }
        }
        Screen.Wounds -> {
            selectedCharacter?.let { char ->
                WoundsScreen(
                    character = char,
                    onCharacterUpdated = { viewModel.updateCharacter(it) },
                    onNavigateToHome = { viewModel.navigateTo(Screen.CharacterDetail) },
                    onNavigateToCharacters = { viewModel.navigateTo(Screen.Characters) },
                    onNavigateToSkills = { viewModel.navigateTo(Screen.Skills) },
                    onNavigateToInventory = { viewModel.navigateTo(Screen.Inventory) },
                    onNavigateToWounds = { viewModel.navigateTo(Screen.Wounds) },
                    onNavigateToSettings = { viewModel.navigateTo(Screen.Settings) }
                )
            }
        }
        Screen.Settings -> {
            SettingsScreen(
                currentTheme = viewModel.currentTheme.value,
                onThemeChanged = { viewModel.updateTheme(it) },
                onNavigateBack = { viewModel.navigateTo(Screen.CharacterDetail) }
            )
        }
    }
}