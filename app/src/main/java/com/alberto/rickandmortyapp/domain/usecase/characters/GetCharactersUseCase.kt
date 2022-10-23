package com.alberto.rickandmortyapp.domain.usecase.characters

import com.alberto.rickandmortyapp.core.base.FlowUseCase
import com.alberto.rickandmortyapp.core.di.coroutines.IoDispatcher
import com.alberto.rickandmortyapp.domain.model.CharacterModel
import com.alberto.rickandmortyapp.domain.repository.CharactersRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val charactersRepository: CharactersRepository
): FlowUseCase<Any?, List<CharacterModel>>(dispatcher) {

    override suspend fun execute(params: Any?): Flow<List<CharacterModel>> = charactersRepository.getCharacters()
}