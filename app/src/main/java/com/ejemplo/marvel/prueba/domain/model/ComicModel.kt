package com.ejemplo.marvel.prueba.domain.model

import android.media.Image
import com.ejemplo.marvel.prueba.data.data_source.dto.Thumbnail

data class ComicModel(

    val id: Int,
    val title: String,
    val description: String,
    val thumbnail: Thumbnail
)

