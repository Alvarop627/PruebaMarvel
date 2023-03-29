package com.ejemplo.marvel.prueba.ui.comicslist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ejemplo.marvel.prueba.domain.use_cases.ComicListByCharacterIdUseCase
import com.ejemplo.marvel.prueba.ui.comicslist.ComicState
import com.ejemplo.marvel.prueba.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ComicViewModel @Inject constructor(
    private val comicListUseCase: ComicListByCharacterIdUseCase
) : ViewModel() {

    private val characterIdState = MutableStateFlow("")
    private val offsetState = MutableStateFlow(0)

    val comicListState: MutableLiveData<ComicState> = MutableLiveData()

    init {
        viewModelScope.launch {
            characterIdState.flatMapLatest { characterId ->
                offsetState.map { offset ->
                    Pair(characterId, offset)
                }
            }.collect { (characterId, offset) ->
                getComicListByCharacterId(characterId, offset)
            }
        }
    }

    fun setCharacterId(characterId: String) {
        characterIdState.value = characterId
    }

    fun setOffset(offset: Int) {
        offsetState.value = offset
    }

    fun getComicListByCharacterId(id: String, offset: Int) = viewModelScope.launch {
        comicListUseCase(id, offset).collect {
            when (it) {
                is State.Success -> {
                    val comicList = it.data ?: emptyList()
                    comicListState.postValue(ComicState(false,comicList))
                }
                is State.Loading -> {
                    comicListState.postValue(ComicState(isLoading = true))
                }
                is State.Error -> {
                    comicListState.postValue(
                        ComicState(
                            error = if(it.data!=null){it.message}else{
                                "The service has returned a null value."
                            }?: "An Unexpected Error"
                        )
                    )
                }
            }
        }
    }

}