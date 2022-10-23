package com.alberto.rickandmortyapp.data.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alberto.rickandmortyapp.data.response.CharacterResponse

@Dao
interface CharacterModelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<CharacterResponse>)

    @Query("SELECT * FROM characterresponse")
    fun getAllCharacters(): PagingSource<Int, CharacterResponse>

    @Query("DELETE FROM characterresponse")
    suspend fun clearAllCharacters()
}