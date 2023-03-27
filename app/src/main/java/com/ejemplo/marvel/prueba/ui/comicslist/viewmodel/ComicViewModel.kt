package com.ejemplo.marvel.prueba.ui.comicslist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ejemplo.marvel.prueba.domain.use_cases.ComicListByCharacterIdUseCase
import com.ejemplo.marvel.prueba.ui.comicslist.ComicState
import com.ejemplo.marvel.prueba.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ComicViewModel @Inject constructor(
    private val comicUseCase: ComicListByCharacterIdUseCase
) : ViewModel() {

    val comicListState: MutableLiveData<ComicState> = MutableLiveData()

    fun getComicsListByCharacterId(id: String) = viewModelScope.launch {
        comicListState.postValue(ComicState(isLoading = true))
        comicUseCase(id).collect {
            when (it) {
                is State.Success -> {
                    comicListState.postValue(it.data?.let { it1 ->
                        ComicState(
                            comicList = it1,
                            isLoading = false
                        )
                    })
                }
                is State.Loading -> {
                    comicListState.postValue(ComicState(isLoading = true))
                }
                is State.Error -> {
                    comicListState.postValue(
                        ComicState(
                            error = it.message ?: "An Unexpected Error"
                        )
                    )
                }
            }
        }
    }

}