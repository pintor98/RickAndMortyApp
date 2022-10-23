package com.alberto.rickandmortyapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alberto.rickandmortyapp.data.dao.CharacterModelDao
import com.alberto.rickandmortyapp.data.dao.RemoteKeysDao
import com.alberto.rickandmortyapp.data.response.CharacterResponse
import com.alberto.rickandmortyapp.domain.model.local.RemoteKeys

@Database(version = 1, entities = [CharacterResponse::class, RemoteKeys::class], exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getRepoDao(): RemoteKeysDao
    abstract fun getCharacterModelDao(): CharacterModelDao
}