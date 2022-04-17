package com.nroncari.tictaccross.domain.mapper

import com.nroncari.tictaccross.data.model.GameConnexionRequest
import com.nroncari.tictaccross.presentation.model.GameConnexionPresentation
import com.nroncari.tictaccross.utils.Mapper

class GameConnexionToRequestMapper : Mapper<GameConnexionPresentation, GameConnexionRequest> {

    override fun map(source: GameConnexionPresentation): GameConnexionRequest {
        return GameConnexionRequest(
            gameId = source.gameId
        )
    }
}