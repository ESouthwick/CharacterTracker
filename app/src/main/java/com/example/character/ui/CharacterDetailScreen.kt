package com.example.character.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.character.data.Character

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreen(
    character: Character,
    onNavigateToCharacters: () -> Unit,
    onNavigateToSkills: () -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToInventory: () -> Unit,
    onNavigateToWounds: () -> Unit
) {
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
                    selected = true,
                    onClick = onNavigateToHome
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Inventory") },
                    label = { Text("Inventory") },
                    selected = false,
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
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Skills Summary
            Card(
                modifier = Modifier.fillMaxWidth(),
                onClick = onNavigateToSkills
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Skills",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = "Total Level: ${character.skills.melee.level + character.skills.magic.level + character.skills.ranged.level + character.skills.defense.level + character.skills.thieving.level + character.skills.gathering.level + character.skills.crafting.level + character.skills.cooking.level}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            // Wounds Summary
            Card(
                modifier = Modifier.fillMaxWidth(),
                onClick = onNavigateToWounds
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Wounds & Deaths",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = "Wounds: ${character.wounds} | Deaths: ${character.deathTally}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            // Inventory Summary
            Card(
                modifier = Modifier.fillMaxWidth(),
                onClick = onNavigateToInventory
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Inventory",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = "Items: ${character.inventory.size}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
} 