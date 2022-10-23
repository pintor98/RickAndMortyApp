package com.alberto.rickandmortyapp.data.repository

import androidx.paging.PagingData
import com.alberto.rickandmortyapp.data.datasource.CharactersDatasource
import com.alberto.rickandmortyapp.data.map.mapToCharacterModel
import com.alberto.rickandmortyapp.data.map.mapToCharacterModelList
import com.alberto.rickandmortyapp.domain.model.CharacterModel
import com.alberto.rickandmortyapp.domain.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val charactersDatasource: CharactersDatasource
): CharactersRepository{

    override fun getCharacters(): Flow<PagingData<CharacterModel>> {
        return charactersDatasource.getCharacters().map { response ->
            mapToCharacterModelList(response)
        }
    }

    override fun getCharacterById(id: Int): Flow<CharacterModel> {
        return charactersDatasource.getCharacterById(id).map { response ->
            mapToCharacterModel(response)
        }
    }
}