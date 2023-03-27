package com.ejemplo.marvel.prueba.data.data_source.dto

data class CharacterData(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<CharacterResult>,
    val total: Int
)