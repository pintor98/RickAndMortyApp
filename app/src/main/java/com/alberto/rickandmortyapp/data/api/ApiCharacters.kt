package com.alberto.rickandmortyapp.data.api

import com.alberto.rickandmortyapp.data.response.CharactersResponse
import retrofit2.http.GET

interface ApiCharacters {

    @GET("character")
    suspend fun getCharacters(): CharactersResponse
}