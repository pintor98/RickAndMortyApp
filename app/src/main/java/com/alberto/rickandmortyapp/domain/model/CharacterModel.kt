package com.alberto.rickandmortyapp.domain.model

data class CharactersModel (
    val results: List<CharacterModel>
)

data class CharacterModel(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val image: String,
    val url: String,
    val created: String
)