package com.alberto.rickandmortyapp.features.charcatersDetail.vm

import androidx.lifecycle.viewModelScope
import com.alberto.rickandmortyapp.core.base.BaseViewModel
import com.alberto.rickandmortyapp.core.delegate.LoadingDelegateInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CharactersDetailViewModel @Inject constructor(
    private val loadingDelegateInterface: LoadingDelegateInterface
): BaseViewModel(), LoadingDelegateInterface by loadingDelegateInterface  {

    val loadingMain = loading.stateIn(viewModelScope, SharingStarted.WhileSubscribed(3000), initialValue = false)

    fun getCharacterById(id: Int) {

    }
}