package com.example.character.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.character.R
import com.example.character.data.Character
import com.example.character.ui.components.CommonNavBar
import com.example.character.ui.components.CommonTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SkillsScreen(
    character: Character,
    onCharacterUpdated: (Character) -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToCharacters: () -> Unit,
    onNavigateToSkills: () -> Unit,
    onNavigateToInventory: () -> Unit,
    onNavigateToWounds: () -> Unit,
    onNavigateToSettings: () -> Unit
) {
    var showGPDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CommonTopAppBar(
                title = "Skills",
                onSettingsClick = onNavigateToSettings,
                gp = character.gp,
                onGPClick = { showGPDialog = true }
            )
        },
        bottomBar = {
            CommonNavBar(
                currentScreen = Screen.Skills,
                onNavigateToHome = onNavigateToHome,
                onNavigateToCharacters = onNavigateToCharacters,
                onNavigateToSkills = onNavigateToSkills,
                onNavigateToInventory = onNavigateToInventory,
                onNavigateToWounds = onNavigateToWounds
            )
        }
    ) { paddingValues ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(listOf(
                Triple("Melee", character.skills.melee.level, R.drawable.ic_attack),
                Triple("Magic", character.skills.magic.level, R.drawable.ic_magic),
                Triple("Ranged", character.skills.ranged.level, R.drawable.ic_ranged),
                Triple("Defense", character.skills.defense.level, R.drawable.ic_defense),
                Triple("Thieving", character.skills.thieving.level, R.drawable.ic_thieving),
                Triple("Gathering", character.skills.gathering.level, R.drawable.ic_gathering),
                Triple("Crafting", character.skills.crafting.level, R.drawable.ic_crafting),
                Triple("Cooking", character.skills.cooking.level, R.drawable.ic_cooking)
            )) { (name, level, iconRes) ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1.125f)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            painter = painterResource(id = iconRes),
                            contentDescription = name,
                            tint = null,
                            modifier = Modifier.size(48.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = name,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                text = "$level",
                                style = MaterialTheme.typography.bodyLarge
                            )
                            repeat(when (name) {
                                "Melee" -> character.skills.melee.xpTokens
                                "Magic" -> character.skills.magic.xpTokens
                                "Ranged" -> character.skills.ranged.xpTokens
                                "Defense" -> character.skills.defense.xpTokens
                                "Thieving" -> character.skills.thieving.xpTokens
                                "Gathering" -> character.skills.gathering.xpTokens
                                "Crafting" -> character.skills.crafting.xpTokens
                                "Cooking" -> character.skills.cooking.xpTokens
                                else -> 0
                            }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_xpl),
                                    contentDescription = "XP Token",
                                    tint = null,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                        ) {
                            IconButton(
                                onClick = {
                                    val updatedSkills = when (name) {
                                        "Melee" -> {
                                            val currentTokens = character.skills.melee.xpTokens
                                            if (currentTokens > 0) {
                                                character.skills.copy(melee = character.skills.melee.copy(xpTokens = currentTokens - 1))
                                            } else character.skills
                                        }
                                        "Magic" -> {
                                            val currentTokens = character.skills.magic.xpTokens
                                            if (currentTokens > 0) {
                                                character.skills.copy(magic = character.skills.magic.copy(xpTokens = currentTokens - 1))
                                            } else character.skills
                                        }
                                        "Ranged" -> {
                                            val currentTokens = character.skills.ranged.xpTokens
                                            if (currentTokens > 0) {
                                                character.skills.copy(ranged = character.skills.ranged.copy(xpTokens = currentTokens - 1))
                                            } else character.skills
                                        }
                                        "Defense" -> {
                                            val currentTokens = character.skills.defense.xpTokens
                                            if (currentTokens > 0) {
                                                character.skills.copy(defense = character.skills.defense.copy(xpTokens = currentTokens - 1))
                                            } else character.skills
                                        }
                                        "Thieving" -> {
                                            val currentTokens = character.skills.thieving.xpTokens
                                            if (currentTokens > 0) {
                                                character.skills.copy(thieving = character.skills.thieving.copy(xpTokens = currentTokens - 1))
                                            } else character.skills
                                        }
                                        "Gathering" -> {
                                            val currentTokens = character.skills.gathering.xpTokens
                                            if (currentTokens > 0) {
                                                character.skills.copy(gathering = character.skills.gathering.copy(xpTokens = currentTokens - 1))
                                            } else character.skills
                                        }
                                        "Crafting" -> {
                                            val currentTokens = character.skills.crafting.xpTokens
                                            if (currentTokens > 0) {
                                                character.skills.copy(crafting = character.skills.crafting.copy(xpTokens = currentTokens - 1))
                                            } else character.skills
                                        }
                                        "Cooking" -> {
                                            val currentTokens = character.skills.cooking.xpTokens
                                            if (currentTokens > 0) {
                                                character.skills.copy(cooking = character.skills.cooking.copy(xpTokens = currentTokens - 1))
                                            } else character.skills
                                        }
                                        else -> character.skills
                                    }
                                    onCharacterUpdated(character.copy(skills = updatedSkills))
                                },
                                modifier = Modifier.size(24.dp)
                            ) {
                                Icon(
                                    Icons.Default.KeyboardArrowDown,
                                    contentDescription = "Decrease XP",
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                            IconButton(
                                onClick = {
                                    val updatedSkills = when (name) {
                                        "Melee" -> {
                                            val currentTokens = character.skills.melee.xpTokens + 1
                                            val newLevel = if (currentTokens > 2) character.skills.melee.level + 1 else character.skills.melee.level
                                            character.skills.copy(melee = character.skills.melee.copy(
                                                xpTokens = if (currentTokens > 2) 0 else currentTokens,
                                                level = newLevel
                                            ))
                                        }
                                        "Magic" -> {
                                            val currentTokens = character.skills.magic.xpTokens + 1
                                            val newLevel = if (currentTokens > 2) character.skills.magic.level + 1 else character.skills.magic.level
                                            character.skills.copy(magic = character.skills.magic.copy(
                                                xpTokens = if (currentTokens > 2) 0 else currentTokens,
                                                level = newLevel
                                            ))
                                        }
                                        "Ranged" -> {
                                            val currentTokens = character.skills.ranged.xpTokens + 1
                                            val newLevel = if (currentTokens > 2) character.skills.ranged.level + 1 else character.skills.ranged.level
                                            character.skills.copy(ranged = character.skills.ranged.copy(
                                                xpTokens = if (currentTokens > 2) 0 else currentTokens,
                                                level = newLevel
                                            ))
                                        }
                                        "Defense" -> {
                                            val currentTokens = character.skills.defense.xpTokens + 1
                                            val newLevel = if (currentTokens > 2) character.skills.defense.level + 1 else character.skills.defense.level
                                            character.skills.copy(defense = character.skills.defense.copy(
                                                xpTokens = if (currentTokens > 2) 0 else currentTokens,
                                                level = newLevel
                                            ))
                                        }
                                        "Thieving" -> {
                                            val currentTokens = character.skills.thieving.xpTokens + 1
                                            val newLevel = if (currentTokens > 2) character.skills.thieving.level + 1 else character.skills.thieving.level
                                            character.skills.copy(thieving = character.skills.thieving.copy(
                                                xpTokens = if (currentTokens > 2) 0 else currentTokens,
                                                level = newLevel
                                            ))
                                        }
                                        "Gathering" -> {
                                            val currentTokens = character.skills.gathering.xpTokens + 1
                                            val newLevel = if (currentTokens > 2) character.skills.gathering.level + 1 else character.skills.gathering.level
                                            character.skills.copy(gathering = character.skills.gathering.copy(
                                                xpTokens = if (currentTokens > 2) 0 else currentTokens,
                                                level = newLevel
                                            ))
                                        }
                                        "Crafting" -> {
                                            val currentTokens = character.skills.crafting.xpTokens + 1
                                            val newLevel = if (currentTokens > 2) character.skills.crafting.level + 1 else character.skills.crafting.level
                                            character.skills.copy(crafting = character.skills.crafting.copy(
                                                xpTokens = if (currentTokens > 2) 0 else currentTokens,
                                                level = newLevel
                                            ))
                                        }
                                        "Cooking" -> {
                                            val currentTokens = character.skills.cooking.xpTokens + 1
                                            val newLevel = if (currentTokens > 2) character.skills.cooking.level + 1 else character.skills.cooking.level
                                            character.skills.copy(cooking = character.skills.cooking.copy(
                                                xpTokens = if (currentTokens > 2) 0 else currentTokens,
                                                level = newLevel
                                            ))
                                        }
                                        else -> character.skills
                                    }
                                    onCharacterUpdated(character.copy(skills = updatedSkills))
                                },
                                modifier = Modifier.size(24.dp)
                            ) {
                                Icon(
                                    Icons.Default.KeyboardArrowUp,
                                    contentDescription = "Increase XP",
                                    modifier = Modifier.size(16.dp)
                                )
                            }
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

