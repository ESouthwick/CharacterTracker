package com.example.character.ui

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import com.example.character.data.Character
import com.example.character.data.Skills
import com.example.character.ui.theme.AppTheme

class MainViewModel : ViewModel() {
    private val _nameInput = mutableStateOf("")
    val nameInput: State<String> = _nameInput

    private val _characters = mutableStateOf<List<Character>>(emptyList())
    val characters: State<List<Character>> = _characters

    private val _selectedCharacter = mutableStateOf<Character?>(null)
    val selectedCharacter: State<Character?> = _selectedCharacter

    private val _currentScreen = mutableStateOf(Screen.Main)
    val currentScreen: State<Screen> = _currentScreen

    private val _currentTheme = mutableStateOf(AppTheme.DEFAULT)
    val currentTheme: State<AppTheme> = _currentTheme

    fun updateNameInput(input: String) {
        _nameInput.value = input
    }

    fun createCharacter(character: Character) {
        _characters.value = _characters.value + character
        _selectedCharacter.value = character
        navigateTo(Screen.CharacterDetail)
    }

    fun selectCharacter(character: Character) {
        _selectedCharacter.value = character
        navigateTo(Screen.CharacterDetail)
    }

    fun updateCharacter(character: Character) {
        _selectedCharacter.value = character
        _characters.value = _characters.value.map { 
            if (it.name == character.name) character else it 
        }
    }

    fun updateSkills(skills: Skills) {
        _selectedCharacter.value?.let { character ->
            updateCharacter(character.copy(skills = skills))
        }
    }

    fun navigateTo(screen: Screen) {
        _currentScreen.value = screen
    }

    fun updateTheme(theme: AppTheme) {
        _currentTheme.value = theme
    }
}

enum class Screen {
    Main,
    CharacterDetail,
    Characters,
    Skills,
    Inventory,
    Wounds,
    Settings
}