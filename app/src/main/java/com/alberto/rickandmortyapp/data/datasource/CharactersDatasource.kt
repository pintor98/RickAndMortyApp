package com.alberto.rickandmortyapp.data.datasource

import com.alberto.rickandmortyapp.data.response.CharactersResponse
import kotlinx.coroutines.flow.Flow

interface CharactersDatasource {
    fun getCharacters(): Flow<CharactersResponse>
}