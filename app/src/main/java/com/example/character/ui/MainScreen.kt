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
    val character by viewModel.character
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
            character?.let { char ->
                CharacterDetailScreen(
                    character = char,
                    onNavigateToHome = { viewModel.navigateTo(Screen.Main) },
                    onNavigateToSkills = { viewModel.navigateTo(Screen.Skills) },
                    onNavigateToInventory = { viewModel.navigateTo(Screen.Inventory) },
                    onNavigateToWounds = { viewModel.navigateTo(Screen.Wounds) }
                )
            }
        }
        Screen.Skills -> {
            SkillsScreen(
                onNavigateToHome = { viewModel.navigateTo(Screen.Main) },
                onNavigateToSkills = { viewModel.navigateTo(Screen.Skills) },
                onNavigateToInventory = { viewModel.navigateTo(Screen.Inventory) },
                onNavigateToWounds = { viewModel.navigateTo(Screen.Wounds) }
            )
        }
        Screen.Inventory -> {
            InventoryScreen(
                onNavigateToHome = { viewModel.navigateTo(Screen.Main) },
                onNavigateToSkills = { viewModel.navigateTo(Screen.Skills) },
                onNavigateToInventory = { viewModel.navigateTo(Screen.Inventory) },
                onNavigateToWounds = { viewModel.navigateTo(Screen.Wounds) }
            )
        }
        Screen.Wounds -> {
            WoundsScreen(
                onNavigateToHome = { viewModel.navigateTo(Screen.Main) },
                onNavigateToSkills = { viewModel.navigateTo(Screen.Skills) },
                onNavigateToInventory = { viewModel.navigateTo(Screen.Inventory) },
                onNavigateToWounds = { viewModel.navigateTo(Screen.Wounds) }
            )
        }
    }
}