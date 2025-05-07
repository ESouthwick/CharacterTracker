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
import com.example.character.data.Skill
import com.example.character.data.Skills
import com.example.character.ui.components.CommonTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SkillsScreen(
    character: Character,
    onSkillUpdated: (Skills) -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToCharacters: () -> Unit,
    onNavigateToSkills: () -> Unit,
    onNavigateToInventory: () -> Unit,
    onNavigateToWounds: () -> Unit,
    onNavigateToSettings: () -> Unit
) {
    Scaffold(
        topBar = {
            CommonTopAppBar(
                title = character.name,
                onSettingsClick = onNavigateToSettings
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
                    selected = false,
                    onClick = onNavigateToInventory
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.DateRange, contentDescription = "Skills") },
                    label = { Text("Skills") },
                    selected = true,
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
            SkillRow(
                name = "Melee",
                icon = Icons.Default.Build,
                skill = character.skills.melee,
                onSkillUpdated = { newSkill ->
                    onSkillUpdated(character.skills.copy(melee = newSkill))
                }
            )
            SkillRow(
                name = "Magic",
                icon = Icons.Default.CheckCircle,
                skill = character.skills.magic,
                onSkillUpdated = { newSkill ->
                    onSkillUpdated(character.skills.copy(magic = newSkill))
                }
            )
            SkillRow(
                name = "Ranged",
                icon = Icons.Default.Phone,
                skill = character.skills.ranged,
                onSkillUpdated = { newSkill ->
                    onSkillUpdated(character.skills.copy(ranged = newSkill))
                }
            )
            SkillRow(
                name = "Defense",
                icon = Icons.Default.Email,
                skill = character.skills.defense,
                onSkillUpdated = { newSkill ->
                    onSkillUpdated(character.skills.copy(defense = newSkill))
                }
            )
            SkillRow(
                name = "Thieving",
                icon = Icons.Default.Lock,
                skill = character.skills.thieving,
                onSkillUpdated = { newSkill ->
                    onSkillUpdated(character.skills.copy(thieving = newSkill))
                }
            )
            SkillRow(
                name = "Gathering",
                icon = Icons.Default.ShoppingCart,
                skill = character.skills.gathering,
                onSkillUpdated = { newSkill ->
                    onSkillUpdated(character.skills.copy(gathering = newSkill))
                }
            )
            SkillRow(
                name = "Crafting",
                icon = Icons.Default.Build,
                skill = character.skills.crafting,
                onSkillUpdated = { newSkill ->
                    onSkillUpdated(character.skills.copy(crafting = newSkill))
                }
            )
            SkillRow(
                name = "Cooking",
                icon = Icons.Default.Notifications,
                skill = character.skills.cooking,
                onSkillUpdated = { newSkill ->
                    onSkillUpdated(character.skills.copy(cooking = newSkill))
                }
            )
        }
    }
}

@Composable
fun SkillRow(
    name: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    skill: Skill,
    onSkillUpdated: (Skill) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(icon, contentDescription = name)
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Level ${skill.level}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        if (skill.xpTokens > 0) {
                            onSkillUpdated(skill.copy(xpTokens = skill.xpTokens - 1))
                        }
                    }
                ) {
                    Icon(Icons.Default.KeyboardArrowDown, contentDescription = "Remove XP Token")
                }
                Text(
                    text = "${skill.xpTokens} XP",
                    style = MaterialTheme.typography.bodyMedium
                )
                IconButton(
                    onClick = {
                        if (skill.xpTokens < 3) {
                            onSkillUpdated(skill.copy(xpTokens = skill.xpTokens + 1))
                        } else {
                            // Level up when XP tokens reach 3
                            onSkillUpdated(skill.copy(level = skill.level + 1, xpTokens = 0))
                        }
                    }
                ) {
                    Icon(Icons.Default.KeyboardArrowUp, contentDescription = "Add XP Token")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InventoryScreen(
    onNavigateToCharacters: () -> Unit,
    onNavigateToSkills: () -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToInventory: () -> Unit,
    onNavigateToWounds: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Inventory") }
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Characters") },
                    label = { Text("Characters") },
                    selected = true,
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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Text("Inventory content coming soon")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WoundsScreen(
    onNavigateToHome: () -> Unit,
    onNavigateToCharacters: () -> Unit,
    onNavigateToSkills: () -> Unit,
    onNavigateToInventory: () -> Unit,
    onNavigateToWounds: () -> Unit,

) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Wounds") }
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Characters") },
                    label = { Text("Characters") },
                    selected = true,
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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Text("Wounds content coming soon")
        }
    }
} 