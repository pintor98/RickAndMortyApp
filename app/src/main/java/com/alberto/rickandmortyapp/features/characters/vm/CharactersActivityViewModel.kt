package com.alberto.rickandmortyapp.features.characters.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.alberto.rickandmortyapp.core.base.BaseViewModel
import com.alberto.rickandmortyapp.core.delegate.LoadingDelegateInterface
import com.alberto.rickandmortyapp.domain.model.CharacterModel
import com.alberto.rickandmortyapp.domain.model.common.CustomException
import com.alberto.rickandmortyapp.domain.usecase.characters.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersActivityViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val loadingDelegateInterface: LoadingDelegateInterface
): BaseViewModel(), LoadingDelegateInterface by loadingDelegateInterface {

    private val _characters = MutableLiveData<PagingData<CharacterModel>>()
    val characters: LiveData<PagingData<CharacterModel>> get() = _characters

    val loadingMain = loading.stateIn(viewModelScope, SharingStarted.WhileSubscribed(3000), initialValue = false)

    fun getAllCharacters() {
        viewModelScope.launch {
            getCharactersUseCase.execute(null)
                .onStart { emitLoading(true) }
                .onCompletion { emitLoading(false) }
                .catch { error -> _error.value = error as CustomException }
                .distinctUntilChanged()
                .cachedIn(viewModelScope)
                .collectLatest { data -> _characters.value = data }
        }
    }
}