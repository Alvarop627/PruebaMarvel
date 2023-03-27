package com.ejemplo.marvel.prueba.data.data_source.dto

data class ComicDTO(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val data: ComicDataContainer,
    val etag: String,
    val status: String
)