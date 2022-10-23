package com.alberto.rickandmortyapp.core.di.delegate

import com.alberto.rickandmortyapp.core.delegate.LoadingDelegate
import com.alberto.rickandmortyapp.core.delegate.LoadingDelegateInterface
import com.alberto.rickandmortyapp.core.di.coroutines.IoDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope

@Module
@InstallIn(SingletonComponent::class)
object DelegatesModule {

    @Provides
    fun bindLoadingDelegate(@IoDispatcher workerScope: CoroutineScope): LoadingDelegateInterface = LoadingDelegate(workerScope)
}