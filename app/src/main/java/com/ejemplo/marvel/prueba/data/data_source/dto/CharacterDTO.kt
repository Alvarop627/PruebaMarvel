package com.ejemplo.marvel.prueba.data.data_source.dto

data class CharacterDTO(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val data: CharacterData,
    val etag: String,
    val status: String
)