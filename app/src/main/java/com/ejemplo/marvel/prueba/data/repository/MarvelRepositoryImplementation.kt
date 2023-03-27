package com.ejemplo.marvel.prueba.data.repository

import com.ejemplo.marvel.prueba.data.data_source.MarvelApi
import com.ejemplo.marvel.prueba.data.data_source.dto.CharacterDTO
import com.ejemplo.marvel.prueba.data.data_source.dto.ComicDTO
import com.ejemplo.marvel.prueba.domain.repository.MarvelRepository
import javax.inject.Inject

class MarvelRepositoryImplementation @Inject constructor(
    private val api: MarvelApi
) : MarvelRepository {
    override suspend fun getCharacterById(id: String): CharacterDTO {
        return api.getCharacterById(id)
    }

    override suspend fun getComicListByCharacterId(id: String): ComicDTO {
        return api.getComicListByCharacterId(id)
    }
}