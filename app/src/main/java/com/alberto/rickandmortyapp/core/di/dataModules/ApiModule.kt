package com.alberto.rickandmortyapp.core.di.dataModules

import com.alberto.rickandmortyapp.data.api.ApiCharacters
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    fun provideApiCharacters(retrofit: Retrofit): ApiCharacters = retrofit.create(ApiCharacters::class.java)
}