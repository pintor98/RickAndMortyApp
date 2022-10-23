package com.alberto.rickandmortyapp.data.datasource

import com.alberto.rickandmortyapp.core.base.toDomainException
import com.alberto.rickandmortyapp.data.api.ApiCharacters
import com.alberto.rickandmortyapp.data.response.CharactersResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharactersDatasourceImpl @Inject constructor(
    private val apiCharacters: ApiCharacters
): CharactersDatasource{

    override fun getCharacters(): Flow<CharactersResponse> {
        return flow {
            emit(
                apiCharacters.getCharacters()
            )
        }.catch { exception -> throw toDomainException(exception) }
    }
}