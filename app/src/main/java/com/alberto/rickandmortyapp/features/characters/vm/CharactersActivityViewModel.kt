package com.alberto.rickandmortyapp.features.characters.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.alberto.rickandmortyapp.core.base.BaseViewModel
import com.alberto.rickandmortyapp.domain.model.CharacterModel
import com.alberto.rickandmortyapp.domain.model.common.CustomException
import com.alberto.rickandmortyapp.domain.usecase.characters.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersActivityViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
): BaseViewModel() {

    private val _characters = MutableLiveData<PagingData<CharacterModel>>()
    val characters: LiveData<PagingData<CharacterModel>> get() = _characters

    fun getAllCharacters() {
        viewModelScope.launch {
            getCharactersUseCase.execute(null)
                .catch {
                        error -> _error.value = error as CustomException
                }
                .distinctUntilChanged()
                .cachedIn(viewModelScope)
                .collectLatest { data -> _characters.value = data }
        }
    }
}