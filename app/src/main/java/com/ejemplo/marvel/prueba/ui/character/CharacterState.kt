package com.ejemplo.marvel.prueba.ui.character

import com.ejemplo.marvel.prueba.domain.model.CharacterModel

data class CharacterState(
    val isLoading : Boolean = false,
    val marvelCharacter : List<CharacterModel> = emptyList(),
    val error : String = ""
)