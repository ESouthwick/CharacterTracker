package com.example.character.data

import android.R.attr.name

data class Character(
    val name: String,
    val wounds: Int = 0,
    val deathTally: Int = 0,
    val inventory: List<Item> = emptyList(),
    val coinPouch: Int = 0,
    val skills: Skills = Skills()
)

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