package com.example.character.data

data class Character(
    val name: String,
    val gold: Int = 0
) {
    override fun toString(): String {
        return "Character: $name"
    }
}