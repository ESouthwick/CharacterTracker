package com.example.character.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.character.R
import com.example.character.data.Character
import com.example.character.data.InventoryItem
import com.example.character.ui.components.CommonNavBar
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
    onNavigateToHealth: () -> Unit,
    onNavigateToSettings: () -> Unit
) {
    var showGPDialog by remember { mutableStateOf(false) }
    var showIngredientDialog by remember { mutableStateOf(false) }
    var showMaterialDialog by remember { mutableStateOf(false) }
    var showFoodDialog by remember { mutableStateOf(false) }
    var showExpandedFab by remember { mutableStateOf(false) }

    val token = listOf("Ingredient", "Material", "Food")
    val ingredients = listOf("Fish", "Meat", "Herb", "Vegetable", "Egg", "Flour", "Fruit")
    val materials = listOf("Wood", "Stone", "Leather", "Thread", "Metal")
    val food = listOf("Lobster", "Pie")

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
            Column {
                if (showExpandedFab) {
                    FloatingActionButton(
                        onClick = { showFoodDialog = true },
                        modifier = Modifier.padding(bottom = 8.dp)
                    ) {
                        Icon(
                            painterResource(
                                id = R.drawable.ic_foodl),
                                contentDescription = "Food",
                                tint = null
                        )
                    }
                    FloatingActionButton(
                        onClick = { showIngredientDialog = true },
                        modifier = Modifier.padding(bottom = 8.dp)
                    ) {
                        Icon(
                            painterResource(
                                id = R.drawable.ic_ingred),
                                contentDescription = "Ingredients",
                                tint = null
                        )
                    }
                    FloatingActionButton(
                        onClick = { showMaterialDialog = true },
                        modifier = Modifier.padding(bottom = 8.dp)
                    ) {
                        Icon(
                            painterResource(
                                id = R.drawable.ic_materials),
                                contentDescription = "Materials",
                                tint = null
                        )
                    }
                }
                FloatingActionButton(
                    onClick = { showExpandedFab = !showExpandedFab }
                ) {
                    Icon(
                        if (showExpandedFab) Icons.Default.Close else Icons.Default.Add,
                        contentDescription = if (showExpandedFab) "Close" else "Add Item"
                    )
                }
            }
        },
        bottomBar = {
            CommonNavBar(
                currentScreen = Screen.Inventory,
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
                .padding(16.dp)
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                val tokenCounts = character.inventory.groupBy { it.name }
                    .mapValues { it.value.sumOf { item -> item.quantity } }
                
                items(tokenCounts.toList().size) { index ->
                    val (name, count) = tokenCounts.toList()[index]
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                painter = painterResource(
                                    id = when (name) {
                                        "Fish" -> R.drawable.ic_fishl
                                        "Meat" -> R.drawable.ic_meatl
                                        "Herb" -> R.drawable.ic_herbsl
                                        "Vegetable" -> R.drawable.ic_vegetablel
                                        "Egg" -> R.drawable.ic_eggl
                                        "Flour" -> R.drawable.ic_flourl
                                        "Fruit" -> R.drawable.ic_fruitl
                                        "Wood" -> R.drawable.ic_woodl
                                        "Stone" -> R.drawable.ic_stonesl
                                        "Leather" -> R.drawable.ic_leatherl
                                        "Thread" -> R.drawable.ic_threadl
                                        "Metal" -> R.drawable.ic_metall
                                        "Lobster" -> R.drawable.ic_lobsters
                                        "Pie" -> R.drawable.ic_piel
                                        else -> R.drawable.ic_bag
                                    }
                                ),
                                contentDescription = name,
                                modifier = Modifier.size(48.dp),
                                tint = null
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = name,
                                style = MaterialTheme.typography.titleMedium,
                                textAlign = TextAlign.Center
                            )
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                IconButton(
                                    onClick = {
                                        val currentInventory = character.inventory.toMutableList()
                                        val existingItem = currentInventory.find { it.name == name }
                                        if (existingItem != null) {
                                            if (existingItem.quantity > 1) {
                                                currentInventory[currentInventory.indexOf(existingItem)] =
                                                    existingItem.copy(quantity = existingItem.quantity - 1)
                                            } else {
                                                currentInventory.remove(existingItem)
                                            }
                                            onCharacterUpdated(character.copy(inventory = currentInventory))
                                        }
                                    },
                                    modifier = Modifier.size(48.dp)
                                ) {
                                    Icon(
                                        Icons.Default.KeyboardArrowDown,
                                        contentDescription = "Decrease",
                                        modifier = Modifier.size(48.dp)
                                    )
                                }
                                Text(
                                    text = "x$count",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                IconButton(
                                    onClick = {
                                        val currentInventory = character.inventory.toMutableList()
                                        val existingItem = currentInventory.find { it.name == name }
                                        if (existingItem != null) {
                                            currentInventory[currentInventory.indexOf(existingItem)] = 
                                                existingItem.copy(quantity = existingItem.quantity + 1)
                                            onCharacterUpdated(character.copy(inventory = currentInventory))
                                        }
                                    },
                                    modifier = Modifier.size(48.dp)
                                ) {
                                    Icon(
                                        Icons.Default.KeyboardArrowUp,
                                        contentDescription = "Increase",
                                        modifier = Modifier.size(48.dp)
                                    )
                                }
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
                                },
                                modifier = Modifier.size(36.dp)
                            ) {
                                Icon(Icons.Default.KeyboardArrowDown,
                                    contentDescription = "Remove GP",
                                    modifier = Modifier.size(36.dp))
                            }
                            IconButton(
                                onClick = {
                                    onCharacterUpdated(character.copy(gp = character.gp + 1))
                                },
                                modifier = Modifier.size(36.dp)
                            ) {
                                Icon(Icons.Default.KeyboardArrowUp,
                                    contentDescription = "Add GP",
                                    modifier = Modifier.size(36.dp))
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

        if (showIngredientDialog) {
            AlertDialog(
                onDismissRequest = { showIngredientDialog = false },
                title = { Text("Add Ingredient") },
                text = {
                    Column {
                        ingredients.forEach { ingredient ->
                            Button(
                                onClick = {
                                    val currentInventory = character.inventory.toMutableList()
                                    val existingItem = currentInventory.find { it.name == ingredient }
                                    if (existingItem != null) {
                                        currentInventory[currentInventory.indexOf(existingItem)] = 
                                            existingItem.copy(quantity = existingItem.quantity + 1)
                                    } else {
                                        currentInventory.add(InventoryItem(ingredient, 1))
                                    }
                                    onCharacterUpdated(character.copy(inventory = currentInventory))
                                    showIngredientDialog = false
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Icon(
                                        painter = painterResource(
                                            id = when (ingredient) {
                                                "Fish" -> R.drawable.ic_fish
                                                "Meat" -> R.drawable.ic_meat
                                                "Herb" -> R.drawable.ic_herbs
                                                "Vegetable" -> R.drawable.ic_vegetable
                                                "Egg" -> R.drawable.ic_egg
                                                "Flour" -> R.drawable.ic_flour
                                                "Fruit" -> R.drawable.ic_fruit
                                                else -> R.drawable.ic_bag
                                            }
                                        ),
                                        contentDescription = ingredient,
                                        modifier = Modifier.size(36.dp),
                                        tint = null
                                    )
                                    Text(ingredient)
                                }
                            }
                        }
                    }
                },
                confirmButton = {
                    TextButton(onClick = { showIngredientDialog = false }) {
                        Text("Cancel")
                    }
                }
            )
        }

        if (showMaterialDialog) {
            AlertDialog(
                onDismissRequest = { showMaterialDialog = false },
                title = { Text("Add Material") },
                text = {
                    Column {
                        materials.forEach { material ->
                            Button(
                                onClick = {
                                    val currentInventory = character.inventory.toMutableList()
                                    val existingItem = currentInventory.find { it.name == material }
                                    if (existingItem != null) {
                                        currentInventory[currentInventory.indexOf(existingItem)] = 
                                            existingItem.copy(quantity = existingItem.quantity + 1)
                                    } else {
                                        currentInventory.add(InventoryItem(material, 1))
                                    }
                                    onCharacterUpdated(character.copy(inventory = currentInventory))
                                    showMaterialDialog = false
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Icon(
                                        painter = painterResource(
                                            id = when (material) {
                                                "Wood" -> R.drawable.ic_wood
                                                "Stone" -> R.drawable.ic_stones
                                                "Leather" -> R.drawable.ic_leather
                                                "Thread" -> R.drawable.ic_thread
                                                "Metal" -> R.drawable.ic_metal
                                                else -> R.drawable.ic_bag
                                            }
                                        ),
                                        contentDescription = material,
                                        modifier = Modifier.size(24.dp),
                                        tint = null
                                    )
                                    Text(material)
                                }
                            }
                        }
                    }
                },
                confirmButton = {
                    TextButton(onClick = { showMaterialDialog = false }) {
                        Text("Cancel")
                    }
                }
            )
        }

        if (showFoodDialog) {
            AlertDialog(
                onDismissRequest = { showFoodDialog = false },
                title = { Text("Add Food") },
                text = {
                    Column {
                        food.forEach { foodItem ->
                            Button(
                                onClick = {
                                    val currentInventory = character.inventory.toMutableList()
                                    val existingItem = currentInventory.find { it.name == foodItem }
                                    if (existingItem != null) {
                                        currentInventory[currentInventory.indexOf(existingItem)] = 
                                            existingItem.copy(quantity = existingItem.quantity + 1)
                                    } else {
                                        currentInventory.add(InventoryItem(foodItem, 1))
                                    }
                                    onCharacterUpdated(character.copy(inventory = currentInventory))
                                    showFoodDialog = false
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Icon(
                                        painter = painterResource(
                                            id = when (foodItem) {
                                                "Lobster" -> R.drawable.ic_lobster
                                                "Pie" -> R.drawable.ic_pie
                                                else -> R.drawable.ic_bag
                                            }
                                        ),
                                        contentDescription = foodItem,
                                        modifier = Modifier.size(36.dp),
                                        tint = null
                                    )
                                    Text(foodItem)
                                }
                            }
                        }
                    }
                },
                confirmButton = {
                    TextButton(onClick = { showFoodDialog = false }) {
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