package com.nroncari.tictaccross.domain.usecase

import com.nroncari.tictaccross.domain.mapper.GameConnexionToRequestMapper
import com.nroncari.tictaccross.domain.repository.GameRepository
import com.nroncari.tictaccross.presentation.model.GameConnexionPresentation

class ConnectGameUseCase(
    private val repository: GameRepository
) {

    private val mapper = GameConnexionToRequestMapper()

    suspend operator fun invoke(gameConnexion: GameConnexionPresentation) =
        repository.connectGame(mapper.map(gameConnexion))

}
