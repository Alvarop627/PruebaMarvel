package com.ejemplo.marvel.prueba.data.data_source.dto

data class Events(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemX>,
    val returned: Int
)