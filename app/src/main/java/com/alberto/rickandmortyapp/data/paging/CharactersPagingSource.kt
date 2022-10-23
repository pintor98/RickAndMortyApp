package com.alberto.rickandmortyapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.alberto.rickandmortyapp.core.base.Constants.DEFAULT_PAGE_INDEX
import com.alberto.rickandmortyapp.core.base.toDomainException
import com.alberto.rickandmortyapp.data.api.ApiCharacters
import com.alberto.rickandmortyapp.data.response.CharacterResponse
import retrofit2.HttpException

class CharactersPagingSource(private val apiCharacters: ApiCharacters): PagingSource<Int, CharacterResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterResponse> {
        val page = params.key ?: DEFAULT_PAGE_INDEX

        return try {
            val response = apiCharacters.getCharacters(page)
            LoadResult.Page(
                response.results, prevKey = if (page == DEFAULT_PAGE_INDEX) null else page - 1,
                nextKey = if (response.results.isEmpty()) null else page + 1
            )
        } catch (exception: HttpException) {
            throw toDomainException(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CharacterResponse>): Int? {
        TODO("Not yet implemented")
    }
}