package com.alberto.rickandmortyapp.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.alberto.rickandmortyapp.core.base.Constants.DEFAULT_PAGE_INDEX
import com.alberto.rickandmortyapp.core.base.toDomainException
import com.alberto.rickandmortyapp.data.api.ApiCharacters
import com.alberto.rickandmortyapp.data.local.AppDatabase
import com.alberto.rickandmortyapp.data.response.CharacterResponse
import com.alberto.rickandmortyapp.domain.model.local.RemoteKeys
import retrofit2.HttpException
import java.io.IOException
import java.io.InvalidObjectException
import java.net.UnknownHostException

@OptIn(ExperimentalPagingApi::class)
class CharactersMediator(
    private val apiCharacters: ApiCharacters,
    private val appDatabase: AppDatabase,
): RemoteMediator<Int, CharacterResponse>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterResponse>
    ): MediatorResult {

        val page = when (val pageKeyData = getKeyPageData(loadType, state)) {
            is MediatorResult.Success -> {
                return pageKeyData
            }
            else -> {
                pageKeyData as Int
            }
        }

        try {
            val response = apiCharacters.getCharacters(page)
            val isEndOfList = response.results.isEmpty()
            appDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    appDatabase.getRepoDao().clearRemoteKeys()
                    appDatabase.getCharacterModelDao().clearAllCharacters()
                }

                val prevKey = if (page == DEFAULT_PAGE_INDEX) null else page - 1
                val nextKey = if (isEndOfList) null else page + 1
                val keys = response.results.map { character ->
                    RemoteKeys(repoId = character.id, prevKey = prevKey, nextKey = nextKey)
                }

                appDatabase.getRepoDao().insertAll(keys)
                appDatabase.getCharacterModelDao().insertAll(response.results)
            }
            return MediatorResult.Success(endOfPaginationReached = isEndOfList)
        } catch (exception: HttpException) {
            throw toDomainException(exception)
        } catch (exception: UnknownHostException) {
            throw toDomainException(exception)
        } catch (exception: IOException) {
            throw toDomainException(exception)
        }
    }

    private suspend fun getKeyPageData(loadType: LoadType, state: PagingState<Int, CharacterResponse>): Any? {
        return when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getClosestRemoteKey(state)
                remoteKeys?.nextKey?.minus(1) ?: DEFAULT_PAGE_INDEX
            }
            LoadType.APPEND -> {
                val remoteKeys = getLastRemoteKey(state) ?: throw InvalidObjectException("Remote key should not be null for $loadType")
                remoteKeys.nextKey
            }
            LoadType.PREPEND -> {
                val remoteKeys = getFirstRemoteKey(state) ?: throw InvalidObjectException("Invalid state, key should not be null")
                remoteKeys.prevKey ?: return MediatorResult.Success(endOfPaginationReached = true)
            }
        }
    }

    private suspend fun getLastRemoteKey(state: PagingState<Int, CharacterResponse>): RemoteKeys? {
        return state.pages
            .lastOrNull { it.data.isNotEmpty() }
            ?.data?.lastOrNull()
            ?.let { character -> appDatabase.getRepoDao().remoteKeysCharacterId(character.id) }
    }

    private suspend fun getFirstRemoteKey(state: PagingState<Int, CharacterResponse>): RemoteKeys? {
        return state.pages
            .firstOrNull() { it.data.isNotEmpty() }
            ?.data?.firstOrNull()
            ?.let { character -> appDatabase.getRepoDao().remoteKeysCharacterId(character.id) }
    }

    private suspend fun getClosestRemoteKey(state: PagingState<Int, CharacterResponse>): RemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { repoId ->
                appDatabase.getRepoDao().remoteKeysCharacterId(repoId)
            }
        }
    }
}