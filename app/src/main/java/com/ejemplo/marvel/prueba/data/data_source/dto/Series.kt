package com.ejemplo.marvel.prueba.data.data_source.dto

data class Series(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemXX>,
    val returned: Int
)