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
import com.example.character.data.Skill
import com.example.character.R
import com.example.character.ui.components.CommonNavBar

@OptIn(ExperimentalMaterial3Api::class)
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
                Icon(
                    painter = painterResource(id = R.drawable.ic_xpl),
                    contentDescription = "XP",
                    tint = null
                )
                Text(
                    text = "${skill.xpTokens} ",
                    style = MaterialTheme.typography.bodyMedium
                )
                IconButton(
                    onClick = {
                        if (skill.xpTokens < 2) {
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
            CommonNavBar(
                currentScreen = Screen.Inventory,
                onNavigateToHome = onNavigateToHome,
                onNavigateToCharacters = onNavigateToCharacters,
                onNavigateToSkills = onNavigateToSkills,
                onNavigateToInventory = onNavigateToInventory,
                onNavigateToWounds = onNavigateToWounds
            )
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
            CommonNavBar(
                currentScreen = Screen.Wounds,
                onNavigateToHome = onNavigateToHome,
                onNavigateToCharacters = onNavigateToCharacters,
                onNavigateToSkills = onNavigateToSkills,
                onNavigateToInventory = onNavigateToInventory,
                onNavigateToWounds = onNavigateToWounds
            )
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