package com.example.character.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonTopAppBar(
    title: String,
    onSettingsClick: () -> Unit,
    showGPLabel: Boolean = true,
    gp: Int = 0,
    onGPClick: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        },
        navigationIcon = {
            IconButton(onClick = onSettingsClick) {
                Icon(Icons.Default.Settings, contentDescription = "Settings")
            }
        },
        actions = {
            if (showGPLabel) {
                TextButton(
                    onClick = onGPClick,
                    modifier = Modifier.padding(end = 8.dp)
                ) {
                    Text(
                        text = "GP: $gp",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    )
} 