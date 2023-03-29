package com.ejemplo.marvel.prueba.data.data_source

import com.ejemplo.marvel.prueba.data.data_source.dto.CharacterDTO
import com.ejemplo.marvel.prueba.data.data_source.dto.ComicDTO
import com.ejemplo.marvel.prueba.utils.GlobalConstants
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApi {

    @Headers("Content-Type: application/json")
    @GET("/v1/public/characters/{characterId}")
    suspend fun getCharacterById(
        @Path("characterId") characterId: String,
        @Query("ts") ts: String = GlobalConstants.timeStamp,
        @Query("apikey") apikey: String = GlobalConstants.API_KEY,
        @Query("hash") hash: String = GlobalConstants.hash(),
    ): CharacterDTO

    @Headers("Content-Type: application/json")
    @GET("/v1/public/characters/{characterId}/comics")
    suspend fun getComicListByCharacterId(
        @Path("characterId") characterId: String,
        @Query("offset") offset: Int,
        @Query("ts") ts: String = GlobalConstants.timeStamp,
        @Query("apikey") apikey: String = GlobalConstants.API_KEY,
        @Query("hash") hash: String = GlobalConstants.hash(),
    ): ComicDTO
}