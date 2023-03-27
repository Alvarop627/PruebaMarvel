package com.ejemplo.marvel.prueba.domain.use_cases

import com.ejemplo.marvel.prueba.domain.model.CharacterModel
import com.ejemplo.marvel.prueba.domain.model.ComicModel
import com.ejemplo.marvel.prueba.domain.repository.MarvelRepository
import com.ejemplo.marvel.prueba.utils.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ComicListByCharacterIdUseCase @Inject constructor(
    private val repository: MarvelRepository
) {
    operator fun invoke(id: String): Flow<State<List<ComicModel>>> = flow {
        try {
            emit(State.Loading())
            val comic = repository.getComicListByCharacterId(id).data.results.map {
                it.toComic()
            }
            emit(State.Success(comic as List<ComicModel>))

        } catch (e: HttpException) {
            emit(State.Error(e.printStackTrace().toString()))
        } catch (e: IOException) {
            emit(State.Error(e.printStackTrace().toString()))
        }
    }
}