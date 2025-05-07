package com.example.character.ui

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
import com.example.character.data.Item
import com.example.character.ui.components.CommonTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InventoryScreen(
    character: Character,
    onCharacterUpdated: (Character) -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToCharacters: () -> Unit,
    onNavigateToSkills: () -> Unit,
    onNavigateToInventory: () -> Unit,
    onNavigateToWounds: () -> Unit,
    onNavigateToSettings: () -> Unit
) {
    var showTokenDialog by remember { mutableStateOf(false) }
    var showGPDialog by remember { mutableStateOf(false) }
    var selectedTokenType by remember { mutableStateOf<TokenType?>(null) }

    Scaffold(
        topBar = {
            CommonTopAppBar(
                title = character.name,
                onSettingsClick = onNavigateToSettings,
                gp = character.gp,
                onGPClick = { showGPDialog = true }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showTokenDialog = true }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Token")
            }
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Characters") },
                    label = { Text("Characters") },
                    selected = false,
                    onClick = onNavigateToCharacters
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Warning, contentDescription = "Wounds") },
                    label = { Text("Wounds") },
                    selected = false,
                    onClick = onNavigateToWounds
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = false,
                    onClick = onNavigateToHome
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Inventory") },
                    label = { Text("Inventory") },
                    selected = true,
                    onClick = onNavigateToInventory
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.DateRange, contentDescription = "Skills") },
                    label = { Text("Skills") },
                    selected = false,
                    onClick = onNavigateToSkills
                )
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(character.inventory) { item ->
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = item.name,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = "x${item.quantity}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }

        if (showTokenDialog) {
            AlertDialog(
                onDismissRequest = { showTokenDialog = false },
                title = { Text("Add to Inventory") },
                text = {
                    Column {
                        TextButton(
                            onClick = {
                                showTokenDialog = false
                                showGPDialog = true
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Add GP")
                        }
                        TokenType.values().forEach { tokenType ->
                            TextButton(
                                onClick = {
                                    selectedTokenType = tokenType
                                    showTokenDialog = false
                                    // Add 5 tokens of the selected type
                                    val newItem = Item(tokenType.name, 5)
                                    val updatedInventory = character.inventory + newItem
                                    onCharacterUpdated(character.copy(inventory = updatedInventory))
                                },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(tokenType.name)
                            }
                        }
                    }
                },
                confirmButton = {
                    TextButton(onClick = { showTokenDialog = false }) {
                        Text("Cancel")
                    }
                }
            )
        }

        if (showGPDialog) {
            AlertDialog(
                onDismissRequest = { showGPDialog = false },
                title = { Text("Manage GP") },
                text = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "${character.gp}",
                            style = MaterialTheme.typography.headlineMedium
                        )
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            IconButton(
                                onClick = {
                                    if (character.gp > 0) {
                                        onCharacterUpdated(character.copy(gp = character.gp - 1))
                                    }
                                }
                            ) {
                                Icon(Icons.Default.KeyboardArrowDown, contentDescription = "Remove GP")
                            }
                            IconButton(
                                onClick = {
                                    onCharacterUpdated(character.copy(gp = character.gp + 1))
                                }
                            ) {
                                Icon(Icons.Default.KeyboardArrowUp, contentDescription = "Add GP")
                            }
                        }
                    }
                },
                confirmButton = {
                    TextButton(onClick = { showGPDialog = false }) {
                        Text("Done")
                    }
                }
            )
        }
    }
}

enum class TokenType {
    INGREDIENT,
    MATERIAL,
    FOOD
} 