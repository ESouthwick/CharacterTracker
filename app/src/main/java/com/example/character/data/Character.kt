package com.example.character.data

data class Character(val name: String) {
    override fun toString(): String {
        return "Character: $name"
    }
}