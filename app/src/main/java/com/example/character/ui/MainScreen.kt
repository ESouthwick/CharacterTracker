package com.example.character.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

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
                        onClick = { viewModel.createCharacter() },
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
                    onNavigateToHome = { viewModel.navigateTo(Screen.CharacterDetail) },
                    onNavigateToCharacters = { viewModel.navigateTo(Screen.Characters) },
                    onNavigateToSkills = { viewModel.navigateTo(Screen.Skills) },
                    onNavigateToInventory = { viewModel.navigateTo(Screen.Inventory) },
                    onNavigateToWounds = { viewModel.navigateTo(Screen.Wounds) }
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
                onCharacterSelected = { viewModel.selectCharacter(it) }
            )
        }
        Screen.Skills -> {
            selectedCharacter?.let { char ->
                SkillsScreen(
                    character = char,
                    onSkillUpdated = { viewModel.updateSkills(it) },
                    onNavigateToHome = { viewModel.navigateTo(Screen.CharacterDetail) },
                    onNavigateToCharacters = { viewModel.navigateTo(Screen.Characters) },
                    onNavigateToSkills = { viewModel.navigateTo(Screen.Skills) },
                    onNavigateToInventory = { viewModel.navigateTo(Screen.Inventory) },
                    onNavigateToWounds = { viewModel.navigateTo(Screen.Wounds) }
                )
            }
        }
        Screen.Inventory -> {
            selectedCharacter?.let { char ->
                InventoryScreen(
                    onNavigateToHome = { viewModel.navigateTo(Screen.CharacterDetail) },
                    onNavigateToCharacters = { viewModel.navigateTo(Screen.Characters) },
                    onNavigateToSkills = { viewModel.navigateTo(Screen.Skills) },
                    onNavigateToInventory = { viewModel.navigateTo(Screen.Inventory) },
                    onNavigateToWounds = { viewModel.navigateTo(Screen.Wounds) }
                )
            }
        }
        Screen.Wounds -> {
            selectedCharacter?.let { char ->
                WoundsScreen(
                    onNavigateToHome = { viewModel.navigateTo(Screen.CharacterDetail) },
                    onNavigateToCharacters = { viewModel.navigateTo(Screen.Characters) },
                    onNavigateToSkills = { viewModel.navigateTo(Screen.Skills) },
                    onNavigateToInventory = { viewModel.navigateTo(Screen.Inventory) },
                    onNavigateToWounds = { viewModel.navigateTo(Screen.Wounds) }
                )
            }
        }
    }
}