package com.ejemplo.marvel.prueba.data.data_source.dto

data class CreatorList(
    val available: Int?,
    val returned: Int?,
    val collectionURI: String?,
    val items: List<CreatorSummary>?
)
