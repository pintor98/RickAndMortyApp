package com.alberto.rickandmortyapp.data.datasource

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.alberto.rickandmortyapp.core.base.Constants.DEFAULT_PAGE_SIZE
import com.alberto.rickandmortyapp.data.response.CharacterResponse
import kotlinx.coroutines.flow.Flow

interface CharactersDatasource {
    fun getCharacters(pagingConfig: PagingConfig = getDefaultPageConfig()): Flow<PagingData<CharacterResponse>>
    fun getCharacterById(id: Int): Flow<CharacterResponse>

    fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(pageSize = DEFAULT_PAGE_SIZE, enablePlaceholders = true)
    }
}
