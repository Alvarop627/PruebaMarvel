package com.ejemplo.marvel.prueba.data.data_source.dto

data class ComicDataContainer(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<ComicResult>,
    val total: Int
)