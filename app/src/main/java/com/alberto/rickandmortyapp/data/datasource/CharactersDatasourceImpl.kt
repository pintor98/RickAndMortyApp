package com.alberto.rickandmortyapp.data.datasource

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.alberto.rickandmortyapp.data.api.ApiCharacters
import com.alberto.rickandmortyapp.data.dao.CharacterModelDao
import com.alberto.rickandmortyapp.data.dao.RemoteKeysDao
import com.alberto.rickandmortyapp.data.local.AppDatabase
import com.alberto.rickandmortyapp.data.paging.CharactersMediator
import com.alberto.rickandmortyapp.data.response.CharacterResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharactersDatasourceImpl @Inject constructor(
    private val apiCharacters: ApiCharacters,
    private val appDatabase: AppDatabase
): CharactersDatasource{

    @OptIn(ExperimentalPagingApi::class)
    override fun getCharacters(pagingConfig: PagingConfig): Flow<PagingData<CharacterResponse>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { appDatabase.getCharacterModelDao().getAllCharacters() },
            remoteMediator = CharactersMediator(apiCharacters, appDatabase)
        ).flow
    }

    override fun getCharacterById(id: Int): Flow<CharacterResponse> {
        return appDatabase.getCharacterModelDao().getCharacterById(id)
    }
}