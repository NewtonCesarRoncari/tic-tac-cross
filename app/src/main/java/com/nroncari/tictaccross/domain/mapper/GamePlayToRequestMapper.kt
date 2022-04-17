package com.nroncari.tictaccross.domain.mapper

import com.nroncari.tictaccross.data.model.GamePlayRequest
import com.nroncari.tictaccross.presentation.model.GamePlayPresentation
import com.nroncari.tictaccross.utils.Mapper

class GamePlayToRequestMapper : Mapper<GamePlayPresentation, GamePlayRequest> {

    override fun map(source: GamePlayPresentation): GamePlayRequest {
        return GamePlayRequest(
            source.gameId,
            source.type,
            source.coordinateX,
            source.coordinateY
        )
    }
}