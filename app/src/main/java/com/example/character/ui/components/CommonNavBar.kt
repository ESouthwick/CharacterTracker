package com.example.character.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
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
    onNavigateToWounds: () -> Unit
) {
    NavigationBar {
        NavigationBarItem(
            icon = {Icon(
                painter = painterResource(id = R.drawable.ic_group),
                contentDescription = "Wounds",
                tint = null
            )},
            label = { Text("Characters") },
            selected = currentScreen == Screen.Characters,
            onClick = onNavigateToCharacters
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_hitpoints),
                    contentDescription = "Wounds",
                    tint = null
                )
            },
            label = { Text("Wounds") },
            selected = currentScreen == Screen.Wounds,
            onClick = onNavigateToWounds
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_house),
                    contentDescription = "Wounds",
                    tint = null
                )
            },
            label = { Text("Home") },
            selected = currentScreen == Screen.CharacterDetail,
            onClick = onNavigateToHome
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baga),
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
                        painter = painterResource(id = R.drawable.ic_bar),
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