package com.ejemplo.marvel.prueba.domain.repository

import com.ejemplo.marvel.prueba.data.data_source.dto.CharacterDTO
import com.ejemplo.marvel.prueba.data.data_source.dto.ComicDTO

interface MarvelRepository {
    suspend fun getCharacterById(id: String): CharacterDTO
    suspend fun getComicListByCharacterId(id: String): ComicDTO
}