package com.ejemplo.marvel.prueba.data.data_source.dto

data class CharacterList(
    val available: Int?,
    val returned: Int?,
    val collectionURI: String?,
    val items: List<CharacterSummary>?
)
