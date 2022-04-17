package com.nroncari.tictaccross.domain.usecase

import com.nroncari.tictaccross.domain.repository.GameRepository
import com.nroncari.tictaccross.presentation.model.GamePresentation

class CreateGameUseCase(
    private val repository: GameRepository
) {

    suspend operator fun invoke(): GamePresentation {
        return repository.createGame()
    }
}