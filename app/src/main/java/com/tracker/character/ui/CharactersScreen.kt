package com.example.character.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.character.data.Character
import com.example.character.ui.components.CommonNavBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharactersScreen(
    characters: List<Character>,
    onNavigateToHome: () -> Unit,
    onNavigateToCharacters: () -> Unit,
    onNavigateToSkills: () -> Unit,
    onNavigateToInventory: () -> Unit,
    onNavigateToHealth: () -> Unit,
    onCharacterSelected: (Character) -> Unit,
    onCreateCharacter: (Character) -> Unit
) {
    var showCreateDialog by remember { mutableStateOf(false) }
    var newCharacterName by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Characters") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showCreateDialog = true }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Create Character")
            }
        },
        bottomBar = {
            CommonNavBar(
                currentScreen = Screen.Characters,
                onNavigateToHome = onNavigateToHome,
                onNavigateToCharacters = onNavigateToCharacters,
                onNavigateToSkills = onNavigateToSkills,
                onNavigateToInventory = onNavigateToInventory,
                onNavigateToHealth = onNavigateToHealth
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(characters) { character ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onCharacterSelected(character) }
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = character.name,
                                style = MaterialTheme.typography.titleLarge
                            )
                            Text(
                                text = "Total Level: ${character.totalLevel}",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                        Text(
                            text = "GP: ${character.gp}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }

        if (showCreateDialog) {
            AlertDialog(
                onDismissRequest = { showCreateDialog = false },
                title = { Text("Create New Character") },
                text = {
                    Column {
                        OutlinedTextField(
                            value = newCharacterName,
                            onValueChange = { newCharacterName = it },
                            label = { Text("Character Name") },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            if (newCharacterName.isNotBlank()) {
                                val newCharacter = Character(name = newCharacterName)
                                onCreateCharacter(newCharacter)
                                onCharacterSelected(newCharacter)
                                showCreateDialog = false
                                newCharacterName = ""
                            }
                        },
                        enabled = newCharacterName.isNotBlank()
                    ) {
                        Text("Create")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            showCreateDialog = false
                            newCharacterName = ""
                        }
                    ) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
} 