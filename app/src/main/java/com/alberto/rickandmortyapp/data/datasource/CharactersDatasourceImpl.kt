package com.alberto.rickandmortyapp.data.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.alberto.rickandmortyapp.core.base.Constants
import com.alberto.rickandmortyapp.core.base.toDomainException
import com.alberto.rickandmortyapp.data.api.ApiCharacters
import com.alberto.rickandmortyapp.data.paging.CharactersPagingSource
import com.alberto.rickandmortyapp.data.response.CharacterResponse
import com.alberto.rickandmortyapp.data.response.CharactersResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharactersDatasourceImpl @Inject constructor(
    private val apiCharacters: ApiCharacters
): CharactersDatasource{

    override fun getCharacters(pagingConfig: PagingConfig): Flow<PagingData<CharacterResponse>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { CharactersPagingSource(apiCharacters) }
        ).flow
    }
}