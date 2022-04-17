package com.nroncari.tictaccross.data.mapper

import com.nroncari.tictaccross.data.model.GameResponse
import com.nroncari.tictaccross.domain.model.GameDomain
import com.nroncari.tictaccross.domain.model.GameStateDomain
import com.nroncari.tictaccross.domain.model.TicToeDomain
import com.nroncari.tictaccross.utils.Mapper

class GameToDomainMapper : Mapper<GameResponse, GameDomain> {

    override fun map(source: GameResponse): GameDomain {
        return GameDomain(
            id = source.id,
            amountPlayers = source.amountPlayers,
            lastTicToe = parseTicToe(source.lastTicToe ?: ""),
            state = returnGameState(source.state),
            board = source.board,
            winner = parseTicToe(source.winner ?: ""),
            xScore = source.xScore,
            oScore = source.oScore
        )
    }

    private fun returnGameState(state: String): GameStateDomain {
        return when (state) {
            "NEW" -> GameStateDomain.NEW
            "RUNNING" -> GameStateDomain.RUNNING
            "FINISHED" -> GameStateDomain.FINISHED
            else -> throw IllegalArgumentException()
        }
    }

    private fun parseTicToe(winner: String): TicToeDomain? {
        return when (winner) {
            "X" -> TicToeDomain.X
            "O" -> TicToeDomain.O
            else -> null
        }
    }
}