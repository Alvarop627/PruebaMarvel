package com.ejemplo.marvel.prueba.ui.comicslist

import com.ejemplo.marvel.prueba.domain.model.ComicModel

data class ComicState(
    val isLoading: Boolean = false,
    val comicList: List<ComicModel> = emptyList(),
    val error: String = ""
)