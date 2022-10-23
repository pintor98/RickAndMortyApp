package com.alberto.rickandmortyapp.features.charcatersDetail.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.alberto.rickandmortyapp.core.base.BaseViewModel
import com.alberto.rickandmortyapp.domain.model.CharacterModel
import com.alberto.rickandmortyapp.domain.usecase.charactersDetail.GetCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersDetailViewModel @Inject constructor(
    private val getCharacterUseCase: GetCharacterUseCase,
): BaseViewModel() {

    private val _character = MutableLiveData<CharacterModel>()
    val character: LiveData<CharacterModel> get() = _character

    fun getCharacterById(id: Int) {
        viewModelScope.launch {
            getCharacterUseCase.execute(id)
                .collect { data -> _character.value = data }
        }
    }
}