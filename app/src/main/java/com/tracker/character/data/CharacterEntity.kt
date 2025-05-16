package com.example.character.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "characters")
@TypeConverters(Converters::class)
data class CharacterEntity(
    @PrimaryKey
    val name: String,
    val skills: Skills,
    val inventory: List<InventoryItem>,
    val groupStorage: List<InventoryItem>,
    val health: Health,
    val gp: Int
) {
    fun toCharacter() = Character(
        name = name,
        skills = skills,
        inventory = inventory,
        groupStorage = groupStorage,
        health = health,
        gp = gp
    )

    companion object {
        fun fromCharacter(character: Character) = CharacterEntity(
            name = character.name,
            skills = character.skills,
            inventory = character.inventory,
            groupStorage = character.groupStorage,
            health = character.health,
            gp = character.gp
        )
    }
} 