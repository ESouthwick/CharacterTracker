package com.example.character.ui

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.character.data.Character
import com.example.character.data.Skills

class MainViewModel : ViewModel() {
    private var _nameInput = mutableStateOf("")
    val nameInput: State<String>
        get() = _nameInput

    private var _characters = mutableStateOf<List<Character>>(emptyList())
    val characters: State<List<Character>>
        get() = _characters

    private var _selectedCharacter = mutableStateOf<Character?>(null)
    val selectedCharacter: State<Character?>
        get() = _selectedCharacter

    private var _currentScreen = mutableStateOf(Screen.Main)
    val currentScreen: State<Screen>
        get() = _currentScreen

    fun updateNameInput(newName: String) {
        _nameInput.value = newName
    }

    fun createCharacter() {
        if (_nameInput.value.isNotBlank()) {
            val newCharacter = Character(_nameInput.value)
            _characters.value = _characters.value + newCharacter
            _selectedCharacter.value = newCharacter
            _nameInput.value = ""
            _currentScreen.value = Screen.CharacterDetail
        }
    }

    fun selectCharacter(character: Character) {
        _selectedCharacter.value = character
        _currentScreen.value = Screen.CharacterDetail
    }

    fun updateSkills(skills: Skills) {
        _selectedCharacter.value?.let { character ->
            _selectedCharacter.value = character.copy(skills = skills)
            _characters.value = _characters.value.map {
                if (it.name == character.name) character.copy(skills = skills) else it
            }
        }
    }

    fun navigateTo(screen: Screen) {
        _currentScreen.value = screen
    }
}

enum class Screen {
    Main,
    CharacterDetail,
    Characters,
    Skills,
    Inventory,
    Wounds
}