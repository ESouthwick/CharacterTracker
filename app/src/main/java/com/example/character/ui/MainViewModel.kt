package com.example.character.ui

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.character.data.Character

class MainViewModel : ViewModel() {
    private var _nameInput = mutableStateOf("")
    val nameInput: State<String>
        get() = _nameInput

    private var _character = mutableStateOf<Character?>(null)
    val character: State<Character?>
        get() = _character

    fun updateNameInput(newName: String) {
        _nameInput.value = newName
    }

    fun createCharacter() {
        if (_nameInput.value.isNotBlank()) {
            _character.value = Character(_nameInput.value)
            _nameInput.value = ""
        }
    }
}