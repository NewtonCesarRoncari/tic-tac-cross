package com.nroncari.tictaccross.domain.usecase

import com.nroncari.tictaccross.domain.mapper.GamePlayToRequestMapper
import com.nroncari.tictaccross.domain.repository.GamePlayRepository
import com.nroncari.tictaccross.presentation.model.GamePlayPresentation

class PlayAgainUseCase(
    private val repository: GamePlayRepository
) {

    private val mapper = GamePlayToRequestMapper()

    suspend operator fun invoke(gamePlay: GamePlayPresentation) =
        repository.playAgain(mapper.map(gamePlay))
}