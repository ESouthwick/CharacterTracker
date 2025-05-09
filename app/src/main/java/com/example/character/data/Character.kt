package com.example.character.data

import android.R.attr.name

data class Character(
    val name: String,
    val skills: Skills = Skills(),
    val inventory: List<InventoryItem> = emptyList(),
    val wounds: Int = 0,
    val deathTally: Int = 0,
    val gp: Int = 0
) {
    val totalLevel: Int
        get() = skills.melee.level + skills.magic.level + skills.ranged.level + skills.defense.level +
                skills.thieving.level + skills.gathering.level + skills.crafting.level + skills.cooking.level
}

data class Skills(
    val melee: Skill = Skill(),
    val magic: Skill = Skill(),
    val ranged: Skill = Skill(),
    val defense: Skill = Skill(),
    val thieving: Skill = Skill(),
    val gathering: Skill = Skill(),
    val crafting: Skill = Skill(),
    val cooking: Skill = Skill()
)

data class Skill(
    val level: Int = 1,
    val xpTokens: Int = 0
)

data class Item(
    val name: String,
    val quantity: Int = 1
)

fun toString(): String {
    return "Character: $name"
}