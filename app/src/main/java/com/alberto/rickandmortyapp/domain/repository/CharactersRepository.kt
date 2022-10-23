package com.alberto.rickandmortyapp.domain.repository

import com.alberto.rickandmortyapp.domain.model.CharacterModel
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
    fun getCharacters(): Flow<List<CharacterModel>>
}