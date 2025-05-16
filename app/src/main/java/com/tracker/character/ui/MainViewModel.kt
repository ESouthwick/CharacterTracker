package com.example.character.ui

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.character.data.*
import com.example.character.ui.theme.AppTheme
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val database = CharacterDatabase.getDatabase(application)
    private val characterDao = database.characterDao()

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

    init {
        viewModelScope.launch {
            characterDao.getAllCharacters()
                .map { entities -> entities.map { it.toCharacter() } }
                .collect { characters ->
                    _characters.value = characters
                    if (characters.isNotEmpty() && _currentScreen.value == Screen.Main) {
                        navigateTo(Screen.Characters)
                    }
                }
        }
    }

    fun updateNameInput(input: String) {
        _nameInput.value = input
    }

    fun createCharacter(character: Character) {
        viewModelScope.launch {
            characterDao.insertCharacter(CharacterEntity.fromCharacter(character))
            _selectedCharacter.value = character
            navigateTo(Screen.CharacterDetail)
        }
    }

    fun selectCharacter(character: Character) {
        _selectedCharacter.value = character
        navigateTo(Screen.CharacterDetail)
    }

    fun updateCharacter(character: Character) {
        viewModelScope.launch {
            characterDao.insertCharacter(CharacterEntity.fromCharacter(character))
            _selectedCharacter.value = character
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
    Health,
    Settings
}