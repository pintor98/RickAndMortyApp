package com.alberto.rickandmortyapp.data.response

import com.google.gson.annotations.SerializedName

data class CharactersResponse(
    @SerializedName("results") val results: List<CharacterResponse>
)

data class CharacterResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("status") val status: String,
    @SerializedName("species") val species: String,
    @SerializedName("type") val type: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("origin") val origin: CharacterObjectsResponse,
    @SerializedName("location") val location: CharacterObjectsResponse,
    @SerializedName("image") val image: String,
    @SerializedName("episode") val episode: List<String>,
    @SerializedName("url") val url: String,
    @SerializedName("created") val created: String
)

data class CharacterObjectsResponse(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)
