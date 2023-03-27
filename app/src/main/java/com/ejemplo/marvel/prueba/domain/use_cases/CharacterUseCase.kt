package com.ejemplo.marvel.prueba.domain.use_cases

import com.ejemplo.marvel.prueba.domain.model.CharacterModel
import com.ejemplo.marvel.prueba.domain.repository.MarvelRepository
import com.ejemplo.marvel.prueba.utils.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CharacterUseCase @Inject constructor(
    private val repository: MarvelRepository
) {
    operator fun invoke(id: String): Flow<State<List<CharacterModel>>> = flow {
        try {
            emit(State.Loading())
            val character = repository.getCharacterById(id).data.results.map {
                it.toCharacter()
            }
            emit(State.Success(character))
        } catch (e: HttpException) {
            emit(State.Error(e.printStackTrace().toString()))
        } catch (e: IOException) {
            emit(State.Error(e.printStackTrace().toString()))
        }
    }
}