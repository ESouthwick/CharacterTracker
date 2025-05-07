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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InventoryScreen(
    character: Character,
    onCharacterUpdated: (Character) -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToCharacters: () -> Unit,
    onNavigateToSkills: () -> Unit,
    onNavigateToInventory: () -> Unit,
    onNavigateToWounds: () -> Unit
) {
    var showTokenDialog by remember { mutableStateOf(false) }
    var selectedTokenType by remember { mutableStateOf<TokenType?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(character.name) }
            )
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Button(
                onClick = { showTokenDialog = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Add Token")
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
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
        }

        if (showTokenDialog) {
            AlertDialog(
                onDismissRequest = { showTokenDialog = false },
                title = { Text("Select Token Type") },
                text = {
                    Column {
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
    }
}

enum class TokenType {
    INGREDIENT,
    MATERIAL,
    FOOD
} 