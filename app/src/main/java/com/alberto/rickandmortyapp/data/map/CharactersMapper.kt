package com.alberto.rickandmortyapp.data.map

import androidx.paging.PagingData
import androidx.paging.map
import com.alberto.rickandmortyapp.data.response.CharacterObjectsResponse
import com.alberto.rickandmortyapp.data.response.CharacterResponse
import com.alberto.rickandmortyapp.domain.model.CharacterModel
import com.alberto.rickandmortyapp.domain.model.CharacterObjectsModel

fun mapToCharacterModelList(characterResponse: PagingData<CharacterResponse>): PagingData<CharacterModel> = characterResponse.map { item -> mapToCharacterModel(item) }

fun mapToCharacterModel(characterResponse: CharacterResponse): CharacterModel = CharacterModel(
    characterResponse.id,
    characterResponse.name,
    characterResponse.status,
    characterResponse.species,
    characterResponse.type,
    characterResponse.gender,
    mapToCharacterObjectsModel(characterResponse.origin),
    mapToCharacterObjectsModel(characterResponse.location),
    characterResponse.image,
    characterResponse.episode,
    characterResponse.url,
    characterResponse.created
)

fun mapToCharacterObjectsModel(charactersObjectsResponse: CharacterObjectsResponse): CharacterObjectsModel = CharacterObjectsModel(
    charactersObjectsResponse.name,
    charactersObjectsResponse.url
)