package com.example.character.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.character.R
import com.example.character.ui.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonNavBar(
    currentScreen: Screen,
    onNavigateToHome: () -> Unit,
    onNavigateToCharacters: () -> Unit,
    onNavigateToSkills: () -> Unit,
    onNavigateToInventory: () -> Unit,
    onNavigateToHealth: () -> Unit
) {
    NavigationBar {
        NavigationBarItem(
            icon = {Icon(
                painter = painterResource(id = R.drawable.ic_group),
                contentDescription = "Health",
                tint = null,
                modifier = Modifier.size(24.dp)
            )},
            label = { Text("Characters") },
            selected = currentScreen == Screen.Characters,
            onClick = onNavigateToCharacters,
            modifier = Modifier.size(24.dp)
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_hitpoints),
                    contentDescription = "Wounds",
                    tint = null,
                    modifier = Modifier.size(24.dp)
                )
            },
            label = { Text("Wounds") },
            selected = currentScreen == Screen.Health,
            onClick = onNavigateToHealth,
            modifier = Modifier.size(24.dp)
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_house),
                    contentDescription = "Wounds",
                    tint = null,
                    modifier = Modifier.size(24.dp)
                )
            },
            label = { Text("Home") },
            selected = currentScreen == Screen.CharacterDetail,
            onClick = onNavigateToHome,
            modifier = Modifier.size(24.dp)
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_bag),
                    contentDescription = "Inventory",
                    tint = null
                )
            },
            label = { Text("Inventory") },
            selected = currentScreen == Screen.Inventory,
            onClick = onNavigateToInventory
        )
        NavigationBarItem(
            icon = {
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_skill),
                        contentDescription = "Skills",
                        tint = null
                    )
                }
            },
            label = { Text("Skills") },
            selected = currentScreen == Screen.Skills,
            onClick = onNavigateToSkills
        )
    }
} 