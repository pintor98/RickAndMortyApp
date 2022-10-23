package com.alberto.rickandmortyapp.core.delegate

import com.alberto.rickandmortyapp.core.di.coroutines.IoDispatcher
import com.alberto.rickandmortyapp.core.extensions.tryOffer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

interface LoadingDelegateInterface {
    val loading: Flow<Boolean>

    suspend fun emitLoading(isLoading: Boolean)
}

internal class LoadingDelegate @Inject constructor(
    @IoDispatcher private val workerScope: CoroutineScope
): LoadingDelegateInterface{
    private val _loading = Channel<Boolean>(2)
    override val loading = _loading.receiveAsFlow()

    override suspend fun emitLoading(isLoading: Boolean) {
        workerScope.launch{
            _loading.tryOffer(isLoading)
        }
    }
}