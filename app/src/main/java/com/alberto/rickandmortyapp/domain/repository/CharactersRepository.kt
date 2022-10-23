package com.alberto.rickandmortyapp.domain.repository

import androidx.paging.PagingData
import com.alberto.rickandmortyapp.domain.model.CharacterModel
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
    fun getCharacters(): Flow<PagingData<CharacterModel>>
    fun getCharacterById(id: Int): Flow<CharacterModel>
}