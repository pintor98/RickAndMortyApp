package com.alberto.rickandmortyapp.data.map

import androidx.paging.PagingData
import androidx.paging.map
import com.alberto.rickandmortyapp.data.response.CharacterResponse
import com.alberto.rickandmortyapp.domain.model.CharacterModel

fun mapToCharacterModelList(characterResponse: PagingData<CharacterResponse>): PagingData<CharacterModel> = characterResponse.map { item -> mapToCharacterModel(item) }

fun mapToCharacterModel(characterResponse: CharacterResponse): CharacterModel = CharacterModel(
    characterResponse.id,
    characterResponse.name,
    characterResponse.status,
    characterResponse.species,
    characterResponse.type,
    characterResponse.gender,
    characterResponse.image,
    characterResponse.url,
    characterResponse.created
)