package com.alberto.rickandmortyapp.domain.usecase.charactersDetail

import com.alberto.rickandmortyapp.core.base.FlowUseCase
import com.alberto.rickandmortyapp.core.di.coroutines.IoDispatcher
import com.alberto.rickandmortyapp.domain.model.CharacterModel
import com.alberto.rickandmortyapp.domain.repository.CharactersRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharacterUseCase@Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val charactersRepository: CharactersRepository
): FlowUseCase<Int, CharacterModel>(dispatcher) {

    override suspend fun execute(params: Int): Flow<CharacterModel> = charactersRepository.getCharacterById(params)
}