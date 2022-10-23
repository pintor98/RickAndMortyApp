package com.alberto.rickandmortyapp.data.api

import com.alberto.rickandmortyapp.data.response.CharactersResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiCharacters {

    @GET("character/")
    suspend fun getCharacters(@Query("page") page: Int?): CharactersResponse
}