package com.nroncari.tictaccross.data.datasource

import com.nroncari.tictaccross.data.mapper.GameToDomainMapper
import com.nroncari.tictaccross.data.model.GamePlayRequest
import com.nroncari.tictaccross.data.service.GamePlayService
import com.nroncari.tictaccross.domain.model.GameDomain

class GamePlayRemoteDataSourceImpl(
    private val service: GamePlayService
) : GamePlayRemoteDataSource {

    private val gameMapper = GameToDomainMapper()

    override suspend fun sendGamePlay(gamePlay: GamePlayRequest): GameDomain {
        return gameMapper.map(service.sendGamePlay(gamePlay))
    }

    override suspend fun playAgain(gamePlay: GamePlayRequest): GameDomain {
        return gameMapper.map(service.playAgain(gamePlay))
    }
}