package com.ejemplo.marvel.prueba.ui.character.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ejemplo.marvel.prueba.domain.use_cases.CharacterUseCase
import com.ejemplo.marvel.prueba.ui.character.CharacterState
import com.ejemplo.marvel.prueba.utils.GlobalConstants
import com.ejemplo.marvel.prueba.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val characterUseCase: CharacterUseCase
) : ViewModel() {

    val characterState: MutableLiveData<CharacterState> = MutableLiveData()
    val thorValue: MutableLiveData<CharacterState> = MutableLiveData()
    val hulkValue: MutableLiveData<CharacterState> = MutableLiveData()
    val captainAmericaValue: MutableLiveData<CharacterState> = MutableLiveData()
    val ironManValue: MutableLiveData<CharacterState> = MutableLiveData()

    fun getCharacterByIdValue(id: String) = viewModelScope.launch {
        characterState.postValue(CharacterState(isLoading = true))
        characterUseCase(id).collect {
            when (it) {
                is State.Success -> {
                    when (id) {
                        GlobalConstants.IRON_MAN_ID -> {
                            ironManValue.postValue(
                                CharacterState(
                                    marvelCharacter = it.data ?: emptyList()
                                )
                            )
                        }
                        GlobalConstants.THOR_ID -> {
                            thorValue.postValue(
                                CharacterState(
                                    marvelCharacter = it.data ?: emptyList()
                                )
                            )
                        }
                        GlobalConstants.HULK_ID -> {
                            hulkValue.postValue(
                                CharacterState(
                                    marvelCharacter = it.data ?: emptyList()
                                )
                            )
                        }
                        GlobalConstants.CAPTAIN_AMERICA_ID -> {
                            captainAmericaValue.postValue(
                                CharacterState(
                                    marvelCharacter = it.data ?: emptyList()
                                )
                            )
                        }

                    }
                    characterState.postValue(CharacterState(isLoading = false))
                }
                is State.Loading -> {
                    characterState.postValue(CharacterState(isLoading = true))
                }
                is State.Error -> {
                    characterState.postValue(
                        CharacterState(
                            error = it.message ?: "An Unexpected Error"
                        )
                    )
                }
            }
        }
    }

}