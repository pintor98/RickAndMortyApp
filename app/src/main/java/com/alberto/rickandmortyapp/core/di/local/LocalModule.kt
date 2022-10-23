package com.alberto.rickandmortyapp.core.di.local

import android.content.Context
import androidx.room.Room
import com.alberto.rickandmortyapp.core.base.Constants.RICK_AND_MORTY_DB
import com.alberto.rickandmortyapp.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context) = Room.databaseBuilder(
                                                                        appContext,
                                                                        AppDatabase::class.java,
                                                                        RICK_AND_MORTY_DB
                                                                      ).build()
}