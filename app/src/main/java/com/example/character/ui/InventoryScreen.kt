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

enum class InventoryTab {
    PERSONAL,
    GROUP
}

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
    var selectedTab by remember { mutableStateOf(InventoryTab.PERSONAL) }
    var currentBag by remember { mutableStateOf(character.inventory) }
    var showTransferDialog by remember { mutableStateOf(false) }
    var transferItem by remember { mutableStateOf<InventoryItem?>(null) }
    var transferAmount by remember { mutableStateOf(1f) }

    val ingredients = listOf("Fish", "Meat", "Herb", "Vegetable", "Egg", "Flour", "Fruit")
    val materials = listOf("Wood", "Stone", "Leather", "Thread", "Metal")
    val food = listOf("Lobster", "Pie")

    LaunchedEffect(selectedTab) {
        currentBag = when (selectedTab) {
            InventoryTab.PERSONAL -> character.inventory
            InventoryTab.GROUP -> character.groupStorage
        }
    }

    LaunchedEffect(character) {
        currentBag = when (selectedTab) {
            InventoryTab.PERSONAL -> character.inventory
            InventoryTab.GROUP -> character.groupStorage
        }
    }

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
        ) {
            TabRow(selectedTabIndex = selectedTab.ordinal) {
                Tab(
                    selected = selectedTab == InventoryTab.PERSONAL,
                    onClick = { selectedTab = InventoryTab.PERSONAL },
                    text = { Text("Personal") }
                )
                Tab(
                    selected = selectedTab == InventoryTab.GROUP,
                    onClick = { selectedTab = InventoryTab.GROUP },
                    text = { Text("Group Storage") }
                )
            }

            when (selectedTab) {
                InventoryTab.PERSONAL -> {
                    GroupStorage(
                        inventory = character.inventory,
                        onCharacterUpdated = { updatedCharacter ->
                            onCharacterUpdated(updatedCharacter)
                            currentBag = updatedCharacter.inventory
                        },
                        character = character,
                        modifier = Modifier.padding(16.dp),
                        showTransferDialog = showTransferDialog,
                        transferItem = transferItem,
                        transferAmount = transferAmount,
                        onTransferAmountChange = { transferAmount = it },
                        onTransferDialogDismiss = { showTransferDialog = false },
                        onTransferItemSelect = { item ->
                            transferItem = item
                            transferAmount = 1f
                            showTransferDialog = true
                        },
                        selectedTab = selectedTab,
                        currentBag = currentBag
                    )
                }
                InventoryTab.GROUP -> {
                    GroupStorage(
                        inventory = character.groupStorage,
                        onCharacterUpdated = { updatedCharacter ->
                            onCharacterUpdated(updatedCharacter)
                            currentBag = updatedCharacter.groupStorage
                        },
                        character = character,
                        modifier = Modifier.padding(16.dp),
                        showTransferDialog = showTransferDialog,
                        transferItem = transferItem,
                        transferAmount = transferAmount,
                        onTransferAmountChange = { transferAmount = it },
                        onTransferDialogDismiss = { showTransferDialog = false },
                        onTransferItemSelect = { item ->
                            transferItem = item
                            transferAmount = 1f
                            showTransferDialog = true
                        },
                        selectedTab = selectedTab,
                        currentBag = currentBag
                    )
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
                                    val currentInventory = currentBag.toMutableList()
                                    val existingItem = currentInventory.find { it.name == ingredient }
                                    if (existingItem != null) {
                                        currentInventory[currentInventory.indexOf(existingItem)] = 
                                            existingItem.copy(quantity = existingItem.quantity + 1)
                                    } else {
                                        currentInventory.add(InventoryItem(ingredient, 1))
                                    }
                                    when (selectedTab) {
                                        InventoryTab.PERSONAL -> {
                                            onCharacterUpdated(character.copy(inventory = currentInventory))
                                            currentBag = currentInventory
                                        }
                                        InventoryTab.GROUP -> {
                                            onCharacterUpdated(character.copy(groupStorage = currentInventory))
                                            currentBag = currentInventory
                                        }
                                    }
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
                                                "Fish" -> R.drawable.ic_fishl
                                                "Meat" -> R.drawable.ic_meatl
                                                "Herb" -> R.drawable.ic_herbsl
                                                "Vegetable" -> R.drawable.ic_vegetablel
                                                "Egg" -> R.drawable.ic_eggl
                                                "Flour" -> R.drawable.ic_flourl
                                                "Fruit" -> R.drawable.ic_fruitl
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
                                    val currentInventory = currentBag.toMutableList()
                                    val existingItem = currentInventory.find { it.name == material }
                                    if (existingItem != null) {
                                        currentInventory[currentInventory.indexOf(existingItem)] = 
                                            existingItem.copy(quantity = existingItem.quantity + 1)
                                    } else {
                                        currentInventory.add(InventoryItem(material, 1))
                                    }
                                    when (selectedTab) {
                                        InventoryTab.PERSONAL -> {
                                            onCharacterUpdated(character.copy(inventory = currentInventory))
                                            currentBag = currentInventory
                                        }
                                        InventoryTab.GROUP -> {
                                            onCharacterUpdated(character.copy(groupStorage = currentInventory))
                                            currentBag = currentInventory
                                        }
                                    }
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
                                    val currentInventory = currentBag.toMutableList()
                                    val existingItem = currentInventory.find { it.name == foodItem }
                                    if (existingItem != null) {
                                        currentInventory[currentInventory.indexOf(existingItem)] = 
                                            existingItem.copy(quantity = existingItem.quantity + 1)
                                    } else {
                                        currentInventory.add(InventoryItem(foodItem, 1))
                                    }
                                    when (selectedTab) {
                                        InventoryTab.PERSONAL -> {
                                            onCharacterUpdated(character.copy(inventory = currentInventory))
                                            currentBag = currentInventory
                                        }
                                        InventoryTab.GROUP -> {
                                            onCharacterUpdated(character.copy(groupStorage = currentInventory))
                                            currentBag = currentInventory
                                        }
                                    }
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

        if (showTransferDialog && transferItem != null) {
            AlertDialog(
                onDismissRequest = { showTransferDialog = false },
                title = { Text("Transfer ${transferItem!!.name}") },
                text = {
                    if (transferItem!!.quantity == 1) {
                        Text("Transfer 1 ${transferItem!!.name} to ${if (selectedTab == InventoryTab.PERSONAL) "Group Storage" else "Personal Inventory"}?")
                    } else {
                        Column {
                            Text("Select amount to transfer:")
                            Slider(
                                value = transferAmount,
                                onValueChange = { transferAmount = it },
                                valueRange = 1f..transferItem!!.quantity.toFloat(),
                                steps = transferItem!!.quantity - 1
                            )
                            Text("Amount: ${transferAmount.toInt()}")
                        }
                    }
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            val amount = if (transferItem!!.quantity == 1) 1 else transferAmount.toInt()
                            val sourceInventory = currentBag.toMutableList()
                            val targetInventory = when (selectedTab) {
                                InventoryTab.PERSONAL -> character.groupStorage.toMutableList()
                                InventoryTab.GROUP -> character.inventory.toMutableList()
                            }

                            // Remove from source
                            val sourceItem = sourceInventory.find { it.name == transferItem!!.name }
                            if (sourceItem != null) {
                                if (sourceItem.quantity > amount) {
                                    sourceInventory[sourceInventory.indexOf(sourceItem)] =
                                        sourceItem.copy(quantity = sourceItem.quantity - amount)
                                } else {
                                    sourceInventory.remove(sourceItem)
                                }
                            }

                            // Add to target
                            val targetItem = targetInventory.find { it.name == transferItem!!.name }
                            targetItem?.let {
                                targetInventory[targetInventory.indexOf(it)] =
                                    it.copy(quantity = it.quantity + amount)
                            } ?: run {
                                targetInventory.add(InventoryItem(transferItem!!.name, amount))
                            }

                            // Update both inventories
                            when (selectedTab) {
                                InventoryTab.PERSONAL -> {
                                    onCharacterUpdated(character.copy(
                                        inventory = sourceInventory,
                                        groupStorage = targetInventory
                                    ))
                                }
                                InventoryTab.GROUP -> {
                                    onCharacterUpdated(character.copy(
                                        inventory = targetInventory,
                                        groupStorage = sourceInventory
                                    ))
                                }
                            }
                            showTransferDialog = false
                        }
                    ) {
                        Text("Transfer")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showTransferDialog = false }) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun GroupStorage(
    inventory: List<InventoryItem>,
    onCharacterUpdated: (Character) -> Unit,
    character: Character,
    modifier: Modifier = Modifier,
    showTransferDialog: Boolean,
    transferItem: InventoryItem?,
    transferAmount: Float,
    onTransferAmountChange: (Float) -> Unit,
    onTransferDialogDismiss: () -> Unit,
    onTransferItemSelect: (InventoryItem) -> Unit,
    selectedTab: InventoryTab,
    currentBag: List<InventoryItem>
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.fillMaxSize()
    ) {
        val tokenCounts = inventory.groupBy { it.name }
            .mapValues { it.value.sumOf { item -> item.quantity } }
        
        items(tokenCounts.toList().size) { index ->
            val (name, count) = tokenCounts.toList()[index]
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
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
                                    "Wood" -> R.drawable.ic_wood
                                    "Stone" -> R.drawable.ic_stones
                                    "Leather" -> R.drawable.ic_leather
                                    "Thread" -> R.drawable.ic_thread
                                    "Metal" -> R.drawable.ic_metal
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
                                    val currentInventory = inventory.toMutableList()
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
                                    val currentInventory = inventory.toMutableList()
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
                    IconButton(
                        onClick = {
                            val item = inventory.find { it.name == name }
                            if (item != null) {
                                onTransferItemSelect(item)
                            }
                        },
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .size(32.dp)
                    ) {
                        Icon(
                            Icons.Default.MoreVert,
                            contentDescription = "Transfer",
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }
    }

    if (showTransferDialog && transferItem != null) {
        AlertDialog(
            onDismissRequest = onTransferDialogDismiss,
            title = { Text("Transfer ${transferItem.name}") },
            text = {
                if (transferItem.quantity == 1) {
                    Text("Transfer 1 ${transferItem.name} to ${if (selectedTab == InventoryTab.PERSONAL) "Group Storage" else "Personal Inventory"}?")
                } else {
                    Column {
                        Text("Select amount to transfer:")
                        Slider(
                            value = transferAmount,
                            onValueChange = onTransferAmountChange,
                            valueRange = 1f..transferItem.quantity.toFloat(),
                            steps = transferItem.quantity - 1
                        )
                        Text("Amount: ${transferAmount.toInt()}")
                    }
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        val amount = if (transferItem.quantity == 1) 1 else transferAmount.toInt()
                        val sourceInventory = currentBag.toMutableList()
                        val targetInventory = when (selectedTab) {
                            InventoryTab.PERSONAL -> character.groupStorage.toMutableList()
                            InventoryTab.GROUP -> character.inventory.toMutableList()
                        }

                        // Remove from source
                        val sourceItem = sourceInventory.find { it.name == transferItem.name }
                        if (sourceItem != null) {
                            if (sourceItem.quantity > amount) {
                                sourceInventory[sourceInventory.indexOf(sourceItem)] =
                                    sourceItem.copy(quantity = sourceItem.quantity - amount)
                            } else {
                                sourceInventory.remove(sourceItem)
                            }
                        }

                        // Add to target
                        val targetItem = targetInventory.find { it.name == transferItem.name }
                        targetItem?.let {
                            targetInventory[targetInventory.indexOf(it)] =
                                it.copy(quantity = it.quantity + amount)
                        } ?: run {
                            targetInventory.add(InventoryItem(transferItem.name, amount))
                        }

                        // Update both inventories
                        when (selectedTab) {
                            InventoryTab.PERSONAL -> {
                                onCharacterUpdated(character.copy(
                                    inventory = sourceInventory,
                                    groupStorage = targetInventory
                                ))
                            }
                            InventoryTab.GROUP -> {
                                onCharacterUpdated(character.copy(
                                    inventory = targetInventory,
                                    groupStorage = sourceInventory
                                ))
                            }
                        }
                        onTransferDialogDismiss()
                    }
                ) {
                    Text("Transfer")
                }
            },
            dismissButton = {
                TextButton(onClick = onTransferDialogDismiss) {
                    Text("Cancel")
                }
            }
        )
    }
}