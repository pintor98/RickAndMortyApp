package com.alberto.rickandmortyapp.data.repository

import com.alberto.rickandmortyapp.data.datasource.CharactersDatasource
import com.alberto.rickandmortyapp.data.map.mapToCharacterModelList
import com.alberto.rickandmortyapp.domain.model.CharacterModel
import com.alberto.rickandmortyapp.domain.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val charactersDatasource: CharactersDatasource
): CharactersRepository{

    override fun getCharacters(): Flow<List<CharacterModel>> {
        return charactersDatasource.getCharacters().map { response ->
            mapToCharacterModelList(response)
        }
    }
}