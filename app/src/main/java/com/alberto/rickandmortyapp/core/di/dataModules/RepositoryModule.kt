package com.alberto.rickandmortyapp.core.di.dataModules

import com.alberto.rickandmortyapp.data.repository.CharactersRepositoryImpl
import com.alberto.rickandmortyapp.domain.repository.CharactersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindCharactersRepository(charactersRepositoryImpl: CharactersRepositoryImpl): CharactersRepository
}