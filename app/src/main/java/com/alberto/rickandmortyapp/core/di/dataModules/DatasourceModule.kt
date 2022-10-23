package com.alberto.rickandmortyapp.core.di.dataModules

import com.alberto.rickandmortyapp.data.datasource.CharactersDatasource
import com.alberto.rickandmortyapp.data.datasource.CharactersDatasourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DatasourceModule {

    @Binds
    abstract fun bindCharactersDatasourceModule(charactersDatasourceImpl: CharactersDatasourceImpl): CharactersDatasource
}