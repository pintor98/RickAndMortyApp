package com.alberto.rickandmortyapp.core.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

abstract class FlowUseCase<in P, R>(private val dispatcher: CoroutineDispatcher) {

    suspend operator fun invoke(parameters: P): Flow<R> {
        return execute(parameters).flowOn(dispatcher)
    }

    abstract suspend fun execute(params: P): Flow<R>
}