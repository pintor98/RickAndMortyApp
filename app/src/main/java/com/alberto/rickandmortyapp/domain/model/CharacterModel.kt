package com.alberto.rickandmortyapp.domain.model

data class CharacterModel(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: CharacterObjectsModel,
    val location: CharacterObjectsModel,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
)

data class CharacterObjectsModel(
    val name: String,
    val url: String
)