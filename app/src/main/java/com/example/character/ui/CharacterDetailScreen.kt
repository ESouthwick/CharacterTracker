package com.example.character.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.character.R
import com.example.character.data.Character
import com.example.character.ui.components.CommonTopAppBar
import com.example.character.ui.components.CommonNavBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreen(
    character: Character,
    onCharacterUpdated: (Character) -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToCharacters: () -> Unit,
    onNavigateToSkills: () -> Unit,
    onNavigateToInventory: () -> Unit,
    onNavigateToHealth: () -> Unit,
    onNavigateToSettings: () -> Unit
) {
    var showGPDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CommonTopAppBar(
                title = character.name,
                onSettingsClick = onNavigateToSettings,
                gp = character.gp,
                onGPClick = { showGPDialog = true }
            )
        },
        bottomBar = {
            CommonNavBar(
                currentScreen = Screen.CharacterDetail,
                onNavigateToHome = onNavigateToHome,
                onNavigateToCharacters = onNavigateToCharacters,
                onNavigateToSkills = onNavigateToSkills,
                onNavigateToInventory = onNavigateToInventory,
                onNavigateToHealth = onNavigateToHealth
            )
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
                            text = "Skills",
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text(
                            text = "Total Level: ${character.totalLevel}",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.weight(1f)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_attack),
                                    contentDescription = "Melee",
                                    tint = null
                                )
                                Text("${character.skills.melee.level}")
                            }
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_magic),
                                    contentDescription = "Magic",
                                    tint = null
                                )
                                Text("${character.skills.magic.level}")
                            }
                        }
                        Column(
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.weight(1f)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_ranged),
                                    contentDescription = "Ranged",
                                    tint = null
                                )
                                Text("${character.skills.ranged.level}")
                            }
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_defense),
                                    contentDescription = "Defense",
                                    tint = null
                                )
                                Text("${character.skills.defense.level}")
                            }
                        }
                        Column(
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.weight(1f)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_thieving),
                                    contentDescription = "Thieving",
                                    tint = null
                                )
                                Text("${character.skills.thieving.level}")
                            }
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_gathering),
                                    contentDescription = "Gathering",
                                    tint = null
                                )
                                Text("${character.skills.gathering.level}")
                            }
                        }
                        Column(
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.weight(1f)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_crafting),
                                    contentDescription = "Crafting",
                                    tint = null
                                )
                                Text("${character.skills.crafting.level}")
                            }
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_cooking),
                                    contentDescription = "Cooking",
                                    tint = null
                                )
                                Text("${character.skills.cooking.level}")
                            }
                        }
                    }
                }
            }

            // Wounds Summary
            Card(
                modifier = Modifier.fillMaxWidth(),
                onClick = onNavigateToHealth
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Wounds",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Current Wounds: ${character.health.wounds}")
                        Text("Death Tally: ${character.health.deathTally}")
                    }
                }
            }

            // Inventory Summary
            Card(
                modifier = Modifier.fillMaxWidth(),
                onClick = onNavigateToInventory
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Inventory",
                        style = MaterialTheme.typography.titleLarge
                    )
                    val tokenCounts = character.inventory.groupBy { it.name }
                        .mapValues { it.value.sumOf { item -> item.quantity } }
                    
                    tokenCounts.forEach { (name, count) ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(name)
                            Text("x$count")
                        }
                    }
                }
            }
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